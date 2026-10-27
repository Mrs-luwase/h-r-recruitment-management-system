package ksw.h_r_recruitment_management_system.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "recruiter")
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "recruiter_room_id")
    private InterviewRoom interviewRoom;

    @ManyToMany
    @JoinTable(name = "recruiter_department")
    private List<Department> departments;

    public Recruiter() {
    }


//getters and setters

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public InterviewRoom getInterviewRoom() {
        return interviewRoom;
    }

    public void setInterviewRoom(InterviewRoom interviewRoom) {
        this.interviewRoom = interviewRoom;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
