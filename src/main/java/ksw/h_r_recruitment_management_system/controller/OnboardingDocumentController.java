package ksw.h_r_recruitment_management_system.controller;

import ksw.h_r_recruitment_management_system.domain.OnboardingDocument;
import ksw.h_r_recruitment_management_system.service.InterviewService;
import ksw.h_r_recruitment_management_system.service.OnboardingDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/onboarding-documents")
public class OnboardingDocumentController {

    @Autowired
    private OnboardingDocumentService onboardingServ;

    @PostMapping("/save")
    public ResponseEntity<String> saveOnboardingDocument(@RequestParam UUID interviewId,
                                                         @RequestBody OnboardingDocument doc) {

        String message = onboardingServ.saveOnboardingDocument(interviewId, doc);

        if (message.equals(InterviewService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.equals(OnboardingDocumentService.NOT_OFFERED)) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/applicant/{email}")
    public ResponseEntity<List<OnboardingDocument>> getDocumentsOfApplicant(@PathVariable String email) {
        return new ResponseEntity<>(onboardingServ.getDocumentsOfApplicant(email), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteOnboardingDocument(@PathVariable UUID id) {

        String message = onboardingServ.deleteOnboardingDocument(id);
        if (message.equals(OnboardingDocumentService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}