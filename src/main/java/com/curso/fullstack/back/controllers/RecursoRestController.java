package com.curso.fullstack.back.controllers;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.fullstack.back.models.services.IUploadService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class RecursoRestController {

    @Autowired
    private IUploadService uploadService;

    @GetMapping("/uploads/img/{foto:.+}") // .jpg,.png,ect el + regex
    public ResponseEntity<Resource> verFoto(@PathVariable(name = "foto") String foto) {
        Resource resource = null;
        try {
            resource = this.uploadService.load(foto);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment; filename=\"%s\"", resource.getFilename()));
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
}
