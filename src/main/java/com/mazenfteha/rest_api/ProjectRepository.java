package com.mazenfteha.rest_api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Page<Project> findBySoftwareEngineerId(Integer softwareEngineerId, Pageable pageable);

}
