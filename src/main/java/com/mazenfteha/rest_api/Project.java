package com.mazenfteha.rest_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Project {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @ManyToOne 
    @JoinColumn(name = "software_engineer_id", nullable = false )
    private SoftwareEngineer softwareEngineer;

    public Project(Integer id, String name, String description, SoftwareEngineer softwareEngineer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.softwareEngineer = softwareEngineer;
    }

    protected Project() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SoftwareEngineer getSoftwareEngineer() {
        return softwareEngineer;
    }

    public void setSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        this.softwareEngineer = softwareEngineer;
    }
}
