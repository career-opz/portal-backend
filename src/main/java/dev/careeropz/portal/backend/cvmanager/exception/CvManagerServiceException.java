package dev.careeropz.portal.backend.cvmanager.exception;

import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.dto.ErrorDTO;
import dev.careeropz.portal.backend.cvmanager.dto.ResponseEnum;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Optional;


@Getter
@ToString
public class CvManagerServiceException extends RuntimeException {

    private APIResponse response;

    public CvManagerServiceException(String ex, APIResponse response) {
        super(String.format("CvManagerServiceException: %s", ex));
        this.response = response;
    }

    public CvManagerServiceException(String ex) {
        super(String.format("CvManagerServiceException: %s", ex));
        this.response = APIResponse.builder()
                .status(ResponseEnum.FAILURE)
                .error(Optional.of
                        (List.of
                                (ErrorDTO.builder()
                                        .field("CvManagerError")
                                        .errorMessage(ex)
                                        .build())))
                .build();
    }
}