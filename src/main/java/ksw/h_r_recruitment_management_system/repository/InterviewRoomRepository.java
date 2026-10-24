package ksw.h_r_recruitment_management_system.repository;

import ksw.h_r_recruitment_management_system.domain.InterviewRoom;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface InterviewRoomRepository extends JpaRepository<InterviewRoom, UUID> {

    Optional<InterviewRoom> findByRoomNumber(int roomNumber);

    boolean existsByRoomNumber(int roomNumber);

    /* Own addition//JPQL practice
    @Query("""
           SELECT r
           FROM InterviewRoom r
           WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))
           """)
    List<InterviewRoom> searchByName(@Param("name") String name);

     */
}
