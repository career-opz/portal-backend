package dev.careeropz.portal.backend.cvmanager.controller;


import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.dto.ResponseEnum;
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
        return new ResponseEntity<>(ex.getResponse(), ex.getResponse().getStatusCode());
    }

//    @ExceptionHandler(Exception.class)
//    ResponseEntity<APIResponse> exception(Exception ex){
//        return new ResponseEntity<>(APIResponse.builder()
//                .status(ResponseEnum.FAILURE)
//                .error(Optional.ofNullable(ex.getMessage()).map(message -> APIResponse.ErrorDTO.builder()
//                        .message(message)
//                        .build()).map(errorDTO -> List.of(errorDTO)).orElse(null))
//                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
