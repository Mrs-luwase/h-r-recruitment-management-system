package ksw.h_r_recruitment_management_system.service;

import ksw.h_r_recruitment_management_system.domain.Applicant;
import ksw.h_r_recruitment_management_system.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepo;

    public static final String NOT_FOUND = "The applicant with that id does not exist";

    public String saveApplicant(Applicant a) {
        applicantRepo.save(a);
        return "Applicant saved successfully";
    }

    public List<Applicant> getAllApplicants() {
        return applicantRepo.findAll();
    }

    public Optional<Applicant> getApplicantById(UUID id) {
        return applicantRepo.findById(id);
    }
    public List<Applicant> getApplicantByLastName(String lastName) {
        return applicantRepo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public String updateApplicant(UUID id,Applicant updated) {

        Optional<Applicant> existing = applicantRepo.findById(id);

          if(existing.isEmpty()) {
              return NOT_FOUND;
          }

          Applicant applicant = existing.get();
          applicant.setFirstName(updated.getFirstName());
          applicant.setLastName(updated.getLastName());
          applicant.setEmail(updated.getEmail());
          applicant.setPhone(updated.getPhone());

          applicantRepo.save(applicant);
          return "Applicant updated successfully";
    }

    public String deleteApplicant(UUID id) {
        if (!applicantRepo.existsById(id)) {
            return NOT_FOUND;
        }
        applicantRepo.deleteById(id);
        return "Applicant deleted successfully";

    }
}
