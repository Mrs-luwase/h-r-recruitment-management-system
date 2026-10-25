package ksw.h_r_recruitment_management_system.domain;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //no column constraints
    private String name;


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
