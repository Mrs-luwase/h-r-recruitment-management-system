package ksw.h_r_recruitment_management_system.controller;



import ksw.h_r_recruitment_management_system.domain.Recruiter;
import ksw.h_r_recruitment_management_system.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterServ;

    @PostMapping("/save")
    public ResponseEntity<String> saveRecruiter(@RequestBody Recruiter rec) {
        String message = recruiterServ.saveRecruiter(rec);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recruiter>> getAllRecruiters() {
        return new ResponseEntity<>(recruiterServ.getAllRecruiters(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecruiterById(@PathVariable UUID id) {
        Optional<Recruiter> rec = recruiterServ.getRecruiterById(id);

        if (rec.isPresent()) {
            return new ResponseEntity<>(rec.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(RecruiterService.NOT_FOUND, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRecruiter(@PathVariable UUID id,
                                                   @RequestBody Recruiter rec) {

        String message = recruiterServ.updateRecruiter(id, rec);

        if (message.equals(RecruiterService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecruiter(@PathVariable UUID id) {

        String message = recruiterServ.deleteRecruiter(id);
        if (message.equals(RecruiterService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
