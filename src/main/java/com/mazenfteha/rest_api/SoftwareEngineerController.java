package com.mazenfteha.rest_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController 
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    @GetMapping()
    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        List<SoftwareEngineer> softwareEngineers = new ArrayList<>();
        softwareEngineers.add(new SoftwareEngineer(1, "John Doe", "Java, Spring Boot"));
        softwareEngineers.add(new SoftwareEngineer(2, "Jane Smith", "Python, Django"));
        softwareEngineers.add(new SoftwareEngineer(3, "Mike Johnson", "JavaScript, React"));
        return softwareEngineers;
    }

}
