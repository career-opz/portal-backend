package dev.careeropz.portal.backend.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.careeropz.portal.backend.cvmanager.exception.TokenExtractionFailedException;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.Map;

@Slf4j
public class JwtUtil {
    public static String extractUserId(String jwtToken) {
        try{
            String payload = jwtToken.split("\\.")[1];
            byte[] decodedPayloadBytes = Base64.getDecoder().decode(payload);
            String decodedPayloadStr = new String(decodedPayloadBytes);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> payloadMap = objectMapper.readValue(decodedPayloadStr, new TypeReference<Map<String, Object>>(){});
            return (String) payloadMap.get("sub");
        }catch(Exception e){
            log.error("Error while extracting user id from jwt token: {}", e.getMessage());
            throw new TokenExtractionFailedException("Error while extracting user id from jwt token");
        }
    }
}
