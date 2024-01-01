package dev.careeropz.portal.backend.cvmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.careeropz.portal.backend.config.ApplicationProperties;
import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.dto.ErrorDTO;
import dev.careeropz.portal.backend.cvmanager.dto.ResponseEnum;
import dev.careeropz.portal.backend.cvmanager.exception.CvManagerServiceException;
import dev.careeropz.portal.backend.util.UriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;
import java.util.Optional;

import static dev.careeropz.portal.backend.cvmanager.controller.CvManagetUrlConstants.CV_MANAGER_PROFILE_BY_ID;

@Service
@Slf4j
public class CvManagerProfileClientService {

    private final RestClient restClient;
    private final ApplicationProperties properties;

    public CvManagerProfileClientService(ApplicationProperties properties) {
        this.properties = properties;
        restClient = RestClient.builder()
                .baseUrl(UriBuilder
                        .builder()
                        .host(properties.getCvManagerHost())
                        .port(properties.getCvManagerPort())
                        .build()
                        .buildHttpUrl())
                .build();
    }

    public APIResponse getProfileById(String userid) {
        try {
            log.info("CvManagerProfileAPIService:getProfileById execution started.");

            String response = restClient
                    .get()
                    .uri(CV_MANAGER_PROFILE_BY_ID, userid)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:getProductById execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("Exception occurred while fetching profile {} from CvManagerService, Exception message {}", userid, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", APIResponse
                    .builder()
                    .status(ResponseEnum.FAILURE)
                    .error(Optional.of(List.of(ErrorDTO.builder()
                            .field("")
                            .errorMessage(String.format("Status %d error occurred while fetching profile from cv manager server", restEx.getStatusCode().value()))
                            .build())))
                    .build());
        } catch (Exception ex) { //TODO: If want handle the JsonProcessingException separately
            log.error("Exception occurred while fetching profile {} from CvManagerService, Exception message {}", userid, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch profile info from CvManagerService");
        }
    }

    public APIResponse createProfile(String body) {

        try {
            log.info("CvManagerProfileAPIService:createProfile execution started.");
            HttpEntity<String> request = new HttpEntity<>(body);

            String response = restClient
                    .post()
                    .uri(CV_MANAGER_PROFILE_BY_ID)
                    .body(request)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:createProduct execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientException ex) {
            log.error("Exception occurred while creating profile {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while creating profile info in CvManagerService");
        } catch (Exception ex) { //TODO: If want handle the JsonProcessingException separately
            log.error("Exception occurred while parsing profile {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while parsing profile info in CvManagerService");
        }
    }

    public APIResponse updateProfileById(String userid, String body) {
        APIResponse apiResponse = null;
        try {
            log.info("CvManagerProfileAPIService:updateProfileById execution started.");
            HttpEntity<String> request = new HttpEntity<>(body);

            apiResponse = restClient
                    .put()
                    .uri(CV_MANAGER_PROFILE_BY_ID, userid)
                    .body(request)
                    .retrieve()
                    .body(APIResponse.class);
        } catch (RestClientException ex) {
            log.error("Exception occurred while updating profile {} from CvManagerService, Exception message {}", userid, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while updating profile info in CvManagerService");
        }
        log.info("ProductService:updateProductById execution ended.");
        return apiResponse;
    }

    private JsonNode parseJson(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(jsonString);
    }
}
