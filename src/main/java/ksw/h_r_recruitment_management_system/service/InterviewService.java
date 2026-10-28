package ksw.h_r_recruitment_management_system.service;

import ksw.h_r_recruitment_management_system.domain.Interview;
import ksw.h_r_recruitment_management_system.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepo;

    public static final String NOT_FOUND = "The interview with that id does not exist";

    public String saveInterview(Interview i) {
        interviewRepo.save(i);
        return "Interview saved successfully";
    }

    public List<Interview> getAllInterviews() {
        return interviewRepo.findAll();
    }

    public Optional<Interview> getInterviewById(UUID id) {
        return interviewRepo.findById(id);
    }
    public String updateInterview(UUID id, Interview updatedInt) {
        Optional<Interview> existing = interviewRepo.findById(id);

        if (existing.isEmpty()) {
            return NOT_FOUND;
        }
        Interview interview = existing.get();
        interview.setInterviewDate(updatedInt.getInterviewDate());
        interview.setVacancyTitle(updatedInt.getVacancyTitle());
        interview.setApplicant(updatedInt.getApplicant());
        interview.setRecruiter(updatedInt.getRecruiter());
        interviewRepo.save(interview);
        return "Interview updated successfully";
    }

    public String deleteInterview(UUID id){
        if(!interviewRepo.existsById(id)){
            return NOT_FOUND;

        }
        interviewRepo.deleteById(id);
        return "Interview deleted successfully";
    }
}
