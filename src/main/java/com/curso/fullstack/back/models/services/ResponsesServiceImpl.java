package com.curso.fullstack.back.models.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponsesServiceImpl implements IResponsesService {

    @Value("${status.message.key}")
    private String MESSAGE_KEY;

    @Override
    public ResponseEntity<?> getInternalError(String message) {
        return new ResponseEntity<>(Map.of(MESSAGE_KEY, message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getNotFoundError(String message) {
        return new ResponseEntity<>(Map.of(MESSAGE_KEY, message), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getStatusOk(Object body) {
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<?> getStatusCreated(Object body) {
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

}
