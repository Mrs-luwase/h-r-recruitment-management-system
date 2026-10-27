package ksw.h_r_recruitment_management_system.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //no column constraints
    private String name;

    //The link to the join table
    @JsonIgnore
    @ManyToMany(mappedBy = "departments")
    private List<Recruiter> recruiters;


    public Department(){
   }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {

        this.id = id;
    }
    public String getName() {

        return name;
    }
    public void setName(String name) {

        this.name = name;
    }

}
