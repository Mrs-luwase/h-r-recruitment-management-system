package ksw.h_r_recruitment_management_system.service;

import ksw.h_r_recruitment_management_system.domain.Department;
import ksw.h_r_recruitment_management_system.domain.InterviewRoom;
import ksw.h_r_recruitment_management_system.domain.Recruiter;
import ksw.h_r_recruitment_management_system.repository.DepartmentRepository;
import ksw.h_r_recruitment_management_system.repository.InterviewRepository;
import ksw.h_r_recruitment_management_system.repository.InterviewRoomRepository;
import ksw.h_r_recruitment_management_system.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private InterviewRoomRepository interviewRoomRepo;

    public static final String NOT_FOUND = "The recruiter with that id does not exist";
    public static final String DEPARTMENT_NOT_FOUND = "That department does not exist";
    public static final String ALREADY_HAS_DEPARTMENT = "The recruiter already has that department";
    public static final String ROOM_NOT_FOUND = "The interview room with that roomNumber does not exist";
    public static final String ROOM_TAKEN = "That interview room is taken by another recruiter";

    public String saveRecruiter(Recruiter r) {
        recruiterRepo.save(r);
        return "Recruiter saved successfully";

    }

    public List<Recruiter> getAllRecruiters() {
        return recruiterRepo.findAll();
    }

    public Optional<Recruiter> getRecruiterById(UUID id) {
        return recruiterRepo.findById(id);
    }

    public String updateRecruiter(UUID id, Recruiter updatedrecruiter) {

        Optional<Recruiter> existing = recruiterRepo.findById(id);

        if (existing.isEmpty()) {
            return NOT_FOUND;
        }
        Recruiter Recr = existing.get();
        Recr.setFirstName(updatedrecruiter.getFirstName());
        Recr.setLastName(updatedrecruiter.getLastName());
        Recr.setEmail(updatedrecruiter.getEmail());
        Recr.setInterviewRoom(updatedrecruiter.getInterviewRoom());
        Recr.setDepartments(updatedrecruiter.getDepartments());
        recruiterRepo.save(Recr);
        return "Recruiter updated successfully";
    }

    public String deleteRecruiter(UUID id) {

        if (!recruiterRepo.existsById(id)) {
            return NOT_FOUND;
        }
        recruiterRepo.deleteById(id);
        return "Recruiter deleted successfully";
    }

    public List<Recruiter> getRecruitersByDepartment(String name) {
        return recruiterRepo.findRecruitersByDepartmentName(name);
    }

    public String addDepartmentToRecruiter(UUID recruiterId, UUID departmentId) {
        Optional<Recruiter> rec = recruiterRepo.findById(recruiterId);
        if (rec.isEmpty()) {
            return NOT_FOUND;
        }
        Optional<Department> dept = departmentRepo.findById(departmentId);
        if (dept.isEmpty()) {
            return DEPARTMENT_NOT_FOUND;
        }
        Recruiter recruiter = rec.get();
        Department department = dept.get();
        if (recruiter.getDepartments().contains(department)) {
            return ALREADY_HAS_DEPARTMENT;
        }
        recruiter.getDepartments().add(department);
        recruiterRepo.save(recruiter);
        return "Department added to the recruiter";
    }

    public String changeInterviewRoom(UUID recruiterId, int roomNumber) {
        Optional<Recruiter> rec = recruiterRepo.findById(recruiterId);
        if (rec.isEmpty()) {
            return NOT_FOUND;
        }
        Optional<InterviewRoom> room = interviewRoomRepo.findByRoomNumber(roomNumber);
        if (room.isEmpty()) {
            return ROOM_NOT_FOUND;

        }
        InterviewRoom newRoom = room.get();
        if (recruiterRepo.existsByInterviewRoomAndIdNot(newRoom, recruiterId)) {
            return ROOM_TAKEN;
        }
        Recruiter recruiter = rec.get();
        recruiter.setInterviewRoom(newRoom);
        recruiterRepo.save(recruiter);
        return "Interview room changed successfully";
    }
}
