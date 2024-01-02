package dev.careeropz.portal.backend.cvmanager.exception;

import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.dto.ErrorDTO;
import dev.careeropz.portal.backend.cvmanager.dto.ResponseEnum;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;


@Getter
@ToString
public class CvManagerServiceException extends RuntimeException {
    private final APIResponse response;

    public CvManagerServiceException(String ex, APIResponse response) {
        super(String.format("CvManagerServiceException: %s", ex));
        this.response = response;
    }

    public CvManagerServiceException(String ex) {
        super(String.format("CvManagerServiceException: %s", ex));
        this.response = APIResponse.builder()
                .status(ResponseEnum.FAILURE)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(List.of
                        (ErrorDTO.builder()
                                .field("CvManagerError")
                                .errorMessage(ex)
                                .build()))
                .build();
    }
}