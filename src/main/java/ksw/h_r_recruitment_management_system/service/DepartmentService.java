package ksw.h_r_recruitment_management_system.service;

import ksw.h_r_recruitment_management_system.domain.Department;
import ksw.h_r_recruitment_management_system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepo;

    public static final String NOT_FOUND = "The department with that id does not exist";

    public String saveDepartment(Department dept) {
        departmentRepo.save(dept);
        return"Department saved successfully";

    }
    public List<Department> getAllDepartments(){
        return departmentRepo.findAll();
    }
    public Optional<Department> getDepartmentById(UUID id) {
        return departmentRepo.findById(id);
    }
    public String updateDepartment(UUID id, Department updatedDept) {

        Optional<Department> existing =  departmentRepo.findById(id);

        if(existing.isEmpty()){
            return NOT_FOUND;
        }
        Department department = existing.get();
        department.setName(updatedDept.getName());
        departmentRepo.save(department);
        return "Department updated successfully";
    }
    public String deleteDepartment(UUID id) {

        if(!departmentRepo.existsById(id)){
            return NOT_FOUND;
        }
        departmentRepo.deleteById(id);
        return "Department deleted successfully";
    }
}
