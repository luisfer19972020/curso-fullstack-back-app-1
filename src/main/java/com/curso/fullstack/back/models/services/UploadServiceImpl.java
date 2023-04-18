package com.curso.fullstack.back.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements IUploadService {

    private static final String UPLOADS_PATH = "uploads";

    @Override
    public Resource load(String fileName) throws MalformedURLException {
        return new UrlResource(this.getPath(fileName).toUri());
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String nuevoArchivo = String.format("%s_%s", UUID.randomUUID(), file.getOriginalFilename());
        Files.copy(file.getInputStream(), this.getPath(nuevoArchivo));
        return nuevoArchivo;
    }

    @Override
    public boolean delete(String fileName) {
        if (fileName != null && fileName.length() > 0) {
            File file = Paths.get("uploads").resolve(fileName).toFile();
            if (file.exists() && file.canRead()) {
                return file.delete();
            }
        }
        return false;
    }

    @Override
    public Path getPath(String fileName) {
        return Paths.get(UPLOADS_PATH).resolve(fileName).toAbsolutePath();
    }

}
