package dev.careeropz.portal.backend.cvmanager.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Builder
@Getter
@Setter
public class APIResponse {
    private ResponseEnum status;
    private HttpStatusCode statusCode;
    private List<ErrorDTO> error;
    private JsonNode result;
}
