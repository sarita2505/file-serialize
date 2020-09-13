package com.java.controller;

import com.java.model.Employee;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class FileController {

    @GetMapping("/active")
    public String active() {
        return "active";
    }
    @PostMapping("/file")
    public ResponseEntity<Long> createEmployee(@RequestBody Employee employee){
        System.out.println("data");
        System.out.println(employee.getResume().length());
        System.out.println(employee.getResume().getName());
        return ResponseEntity.ok(employee.getResume().length());
    }
    @PostMapping(value = "/multipartFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> createFile(@RequestParam("file")MultipartFile multipartFile){
        return ResponseEntity.ok(multipartFile.getSize());
    }
}
