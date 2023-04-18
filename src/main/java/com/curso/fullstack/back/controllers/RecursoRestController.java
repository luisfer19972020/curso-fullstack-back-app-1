package com.curso.fullstack.back.controllers;

import java.net.MalformedURLException;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class RecursoRestController {
    @GetMapping("/uploads/img/{foto:.+}") // .jpg,.png,ect el + regex
    public ResponseEntity<Resource> verFoto(@PathVariable(name = "foto") String foto) {
        Resource resource = null;
        try {
            resource = new UrlResource(Paths.get("uploads").resolve(foto).toAbsolutePath().toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment; filename=\"%s\"", resource.getFilename()));
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
}
