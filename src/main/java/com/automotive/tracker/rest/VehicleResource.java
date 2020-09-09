package com.automotive.tracker.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class VehicleResource {


    @GetMapping
    public String exampleTest() {
        return "Hello World";
    }

}
