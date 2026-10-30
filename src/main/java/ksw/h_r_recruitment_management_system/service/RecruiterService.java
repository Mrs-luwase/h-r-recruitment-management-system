package ksw.h_r_recruitment_management_system.service;

import ksw.h_r_recruitment_management_system.domain.Recruiter;
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

    public static final String NOT_FOUND = "The recruiter with that id does not exist";

    public String saveRecruiter(Recruiter r) {
        recruiterRepo.save(r);
        return "Recruiter saved successfully";

    }
    public List<Recruiter> getAllRecruiters(){
        return recruiterRepo.findAll();
    }
    public Optional<Recruiter> getRecruiterById(UUID id) {
        return recruiterRepo.findById(id);
    }
    public String updateRecruiter(UUID id, Recruiter updatedrecruiter) {

        Optional<Recruiter> existing =  recruiterRepo.findById(id);

        if(existing.isEmpty()){
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

        if(!recruiterRepo.existsById(id)){
            return NOT_FOUND;
        }
        recruiterRepo.deleteById(id);
        return "Recruiter deleted successfully";
    }
    public List<Recruiter> getRecruitersByDepartment(String name) {
        return recruiterRepo.findRecruitersByDepartmentName(name);
    }
}
