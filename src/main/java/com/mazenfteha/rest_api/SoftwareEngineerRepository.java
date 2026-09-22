package com.mazenfteha.rest_api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Integer> {

    Page<SoftwareEngineer> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
