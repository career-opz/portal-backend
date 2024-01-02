package dev.careeropz.portal.backend.cvmanager.service;

import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.dto.ErrorDTO;
import dev.careeropz.portal.backend.cvmanager.dto.ResponseEnum;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

public class CommonFunctions {
    public static APIResponse getErrorApiResponse(RestClientResponseException restEx) {
        return APIResponse
                .builder()
                .status(ResponseEnum.FAILURE)
                .statusCode(restEx.getStatusCode())
                .error(List.of(ErrorDTO.builder()
                        .field("")
                        .errorMessage(restEx.getMessage())
                        .build()))
                .build();
    }

}
