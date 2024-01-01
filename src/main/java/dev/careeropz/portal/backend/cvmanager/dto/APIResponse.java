package dev.careeropz.portal.backend.cvmanager.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Builder
@Getter
@Setter
public class APIResponse {

    ResponseEnum status;
    Optional<List<ErrorDTO>> error;
    JsonNode result;

    public String toString() {
        return "APIResponse(status=" + this.getStatus() + ", error=" + this.getError() + ", result=" + this.getResult() + ")";
    }
}
