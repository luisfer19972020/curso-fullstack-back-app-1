package com.curso.fullstack.back.models.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IResponsesService {
    public ResponseEntity<?> getInternalError(String message);

    public ResponseEntity<?> getBadRequest(List<String> messages);

    public ResponseEntity<?> getNotFoundError(String message);

    public ResponseEntity<?> getStatusOk(Object body);

    public ResponseEntity<?> getStatusCreated(Object body);
}
