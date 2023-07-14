package com.example.rsa_aes.controller;

import com.example.rsa_aes.service.FileEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@Controller
public class FileController {

    @Autowired
    private FileEntityService fileEntityService;

    @GetMapping(path = "file/process/{path}")
    public ResponseEntity<Void> processFile(@PathVariable("path") String path){
        try {
            fileEntityService.processFile(path);
            return ResponseEntity.ok().body(null);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

