package ksw.h_r_recruitment_management_system.repository;


import ksw.h_r_recruitment_management_system.domain.InterviewRoom;
import ksw.h_r_recruitment_management_system.domain.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, UUID> {

    @Query("""
             SELECT DISTINCT r
             FROM Recruiter r JOIN r.departments d
             WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))
           """)
    List<Recruiter> findRecruitersByDepartmentName(@Param("name")String name);

    // Does another recruiter (not this one) already use this interview room?
    boolean existsByInterviewRoomAndIdNot(InterviewRoom interviewRoom, UUID id);
}