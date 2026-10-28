package ksw.h_r_recruitment_management_system.controller;



import ksw.h_r_recruitment_management_system.domain.Interview;
import ksw.h_r_recruitment_management_system.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewServ;

    @PostMapping("/save")
    public ResponseEntity<String> saveInterview(@RequestBody Interview i) {
        String message = interviewServ.saveInterview(i);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Interview>> getAllInterviews() {
        return new ResponseEntity<>(interviewServ.getAllInterviews(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInterviewById(@PathVariable UUID id) {
        Optional<Interview> i = interviewServ.getInterviewById(id);

        if (i.isPresent()) {
            return new ResponseEntity<>(i.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(InterviewService.NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateInterview(@PathVariable UUID id, @RequestBody Interview i) {

        String message = interviewServ.updateInterview(id, i);

        if (message.equals(InterviewService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInterview(@PathVariable UUID id) {
        String message = interviewServ.deleteInterview(id);

        if(message.equals(InterviewService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
   }

}
