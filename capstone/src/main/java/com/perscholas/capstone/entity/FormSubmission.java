package com.perscholas.capstone.entity;
import jakarta.persistence.*;

@Entity
public class FormSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String details;
    private String status;

    public FormSubmission() {
    }

    public FormSubmission(String name, String email, String details) {
        this.name = name;
        this.email = email;
        this.details = details;
        this.status = "Pending";
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public Long getId() {
        return id;
    }




}
