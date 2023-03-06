package com.curso.fullstack.back.models.services;

import org.springframework.http.ResponseEntity;

public interface IResponsesService {
    public ResponseEntity<?> getInternalError(String message);

    public ResponseEntity<?> getNotFoundError(String message);

    public ResponseEntity<?> getStatusOk(Object body);

    public ResponseEntity<?> getStatusCreated(Object body);
}
