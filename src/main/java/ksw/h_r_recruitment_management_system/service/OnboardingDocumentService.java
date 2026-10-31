package ksw.h_r_recruitment_management_system.service;

import ksw.h_r_recruitment_management_system.domain.Interview;
import ksw.h_r_recruitment_management_system.domain.InterviewStatus;
import ksw.h_r_recruitment_management_system.domain.OnboardingDocument;
import ksw.h_r_recruitment_management_system.repository.InterviewRepository;
import ksw.h_r_recruitment_management_system.repository.OnboardingDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OnboardingDocumentService {

    @Autowired
    private OnboardingDocumentRepository onboardingRepo;

    @Autowired
    private InterviewRepository interviewRepo;

    public static final String NOT_FOUND = "The onboarding document with that id does not exist";
    public static final String NOT_OFFERED = "Onboarding documents are only for OFFERED interviews";
    public static final String SAVED = "Onboarding document saved successfully";

    public String saveOnboardingDocument(UUID interviewId, OnboardingDocument doc) {
        Optional<Interview> i= interviewRepo.findById(interviewId);
        if (i.isEmpty()) {
            return InterviewService.NOT_FOUND;
        }
        if (i.get().getStatus() != InterviewStatus.OFFERED) {
            return NOT_OFFERED;
        }
        doc.setId(null);
        doc.setInterview(i.get());
        doc.setReceivedDate(LocalDate.now());
        onboardingRepo.save(doc);
        return SAVED;
    }

    public List<OnboardingDocument> getDocumentsOfApplicant(String email) {
        return onboardingRepo.findDocumentsOfApplicant(email);
    }

    public String deleteOnboardingDocument(UUID id) {
        if (!onboardingRepo.existsById(id)) {
            return NOT_FOUND;
        }
        onboardingRepo.deleteById(id);
        return "Onboarding document deleted successfully";
    }
}