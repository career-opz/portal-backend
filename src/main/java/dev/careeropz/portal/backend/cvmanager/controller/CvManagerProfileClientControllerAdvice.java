package dev.careeropz.portal.backend.cvmanager.controller;


import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.dto.ResponseEnum;
import dev.careeropz.portal.backend.cvmanager.exception.CvManagerServiceException;
import dev.careeropz.portal.backend.cvmanager.exception.TokenExtractionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class CvManagerProfileClientControllerAdvice {

    @ExceptionHandler(CvManagerServiceException.class)
    ResponseEntity<APIResponse> cvManagerServiceException(CvManagerServiceException ex){
        return new ResponseEntity<>(ex.getResponse(), ex.getResponse().getStatusCode());
    }

    @ExceptionHandler(TokenExtractionFailedException.class)
    ResponseEntity<APIResponse> tokenExtractionFailedException(TokenExtractionFailedException ex){
        return new ResponseEntity<>(APIResponse.builder()
                .status(ResponseEnum.FAILURE)
                .statusCode(HttpStatus.UNAUTHORIZED)
                .build(), HttpStatus.UNAUTHORIZED);
    }
}
