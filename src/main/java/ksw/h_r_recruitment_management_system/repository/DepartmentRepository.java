package ksw.h_r_recruitment_management_system.repository;

import ksw.h_r_recruitment_management_system.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {

    @Query("""
            SELECT d FROM Department d
            WHERE d.recruiters IS EMPTY
            ORDER BY d.name ASC
            """)
    List<Department> findUnusedDepartments();
}
