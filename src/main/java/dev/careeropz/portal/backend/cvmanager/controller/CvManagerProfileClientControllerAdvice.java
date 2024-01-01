package dev.careeropz.portal.backend.cvmanager.controller;


import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.exception.CvManagerServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class CvManagerProfileClientControllerAdvice {

    @ExceptionHandler(CvManagerServiceException.class)
    ResponseEntity<APIResponse> cvManagerServiceException(CvManagerServiceException ex){
        return new ResponseEntity<>(ex.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
