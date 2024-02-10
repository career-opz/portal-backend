package dev.careeropz.portal.backend.cvmanager.controller;


import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.dto.ResponseEnum;
import dev.careeropz.portal.backend.cvmanager.exception.CvManagerServiceException;
import dev.careeropz.portal.backend.cvmanager.exception.TokenExtractionFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CvManagerControllerAdvice {

    @ExceptionHandler(CvManagerServiceException.class)
    ResponseEntity<APIResponse> cvManagerServiceException(CvManagerServiceException ex){
        log.error("CvManagerControllerAdvice::cvManagerServiceException :: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getResponse(), ex.getResponse().getStatusCode());
    }

    @ExceptionHandler(TokenExtractionFailedException.class)
    ResponseEntity<APIResponse> tokenExtractionFailedException(TokenExtractionFailedException ex){
        log.error("CvManagerControllerAdvice::tokenExtractionFailedException :: {}", ex.getMessage());
        return new ResponseEntity<>(APIResponse.builder()
                .status(ResponseEnum.FAILURE)
                .statusCode(HttpStatus.UNAUTHORIZED)
                .build(), HttpStatus.UNAUTHORIZED);
    }
}
