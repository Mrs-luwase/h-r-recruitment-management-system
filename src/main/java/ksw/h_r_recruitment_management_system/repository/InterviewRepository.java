package ksw.h_r_recruitment_management_system.repository;

import ksw.h_r_recruitment_management_system.domain.Interview;
import ksw.h_r_recruitment_management_system.domain.InterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, UUID> {

    List<Interview> findByStatusOrderByInterviewDateAsc(InterviewStatus status);

    List<Interview> findByInterviewDateBetweenOrderByInterviewDateAsc(LocalDate start, LocalDate end);

    //Does this recruiter already have an interview on this whose status is not the given one (REJECTED)
    boolean existsByRecruiterIdAndInterviewDateAndStatusNot(UUID recruiterId, LocalDate interviewDate, InterviewStatus status);


}
