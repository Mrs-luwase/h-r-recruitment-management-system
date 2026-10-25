package ksw.h_r_recruitment_management_system.controller;



import ksw.h_r_recruitment_management_system.domain.Department;
import ksw.h_r_recruitment_management_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentServ;

    @PostMapping("/save")
    public ResponseEntity<String> saveDepartment(@RequestBody Department dept) {
        String message = departmentServ.saveDepartment(dept);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<>(departmentServ.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable UUID id) {
        Optional<Department> dept = departmentServ.getDepartmentById(id);

        if (dept.isPresent()) {
            return new ResponseEntity<>(dept.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(DepartmentService.NOT_FOUND, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable UUID id,
                                           @RequestBody Department dept) {

        String message = departmentServ.updateDepartment(id, dept);

        if (message.equals(DepartmentService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable UUID id) {

        String message = departmentServ.deleteDepartment(id);
        if (message.equals(DepartmentService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
