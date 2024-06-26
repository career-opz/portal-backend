package dev.careeropz.portal.backend.cvmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import dev.careeropz.commons.userinfo.UserProfileCheckRequestDto;
import dev.careeropz.portal.backend.config.ApplicationProperties;
import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.dto.ResponseEnum;
import dev.careeropz.portal.backend.cvmanager.exception.CvManagerServiceException;
import dev.careeropz.portal.backend.util.UriBuilder;
import io.swagger.v3.core.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Base64;

import static dev.careeropz.portal.backend.cvmanager.controller.CvManagetUrlConstants.*;
import static dev.careeropz.portal.backend.cvmanager.service.CommonFunctions.getErrorApiResponse;
import static dev.careeropz.portal.backend.cvmanager.service.CommonFunctions.parseJson;

@Service
@Slf4j
public class CvUserProfileService {

    private final RestClient restClient;

    public CvUserProfileService(ApplicationProperties properties) {
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
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("Exception occurred while fetching profile {} from CvManagerService, Exception message {}", userid, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch profile info from CvManagerService");
        }
    }

    public APIResponse createProfile(String body) {

        try {
            log.info("CvManagerProfileAPIService:createProfile execution started.");
            JsonNode reqBody = parseJson(body);

            String response = restClient
                    .post()
                    .uri(CV_MANAGER_PROFILE)
                    .body(reqBody)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:createProduct execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException ex) {
            log.error("Exception occurred while parsing profile {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while parsing profile info in CvManagerService", getErrorApiResponse(ex));
        } catch (Exception ex) {
            log.error("Exception occurred while parsing profile {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while parsing profile info in CvManagerService");
        }
    }

    public APIResponse updateProfileById(String userid, String body) {
        try {
            log.info("CvManagerProfileAPIService:updateProfileById execution started.");
            JsonNode reqBody = parseJson(body);

            String response = restClient
                    .put()
                    .uri(CV_MANAGER_PROFILE_BY_ID, userid)
                    .body(reqBody)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:updateProductById execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();
        } catch (RestClientResponseException ex) {
            log.error("Exception occurred while updating profile {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while updating profile info in CvManagerService", getErrorApiResponse(ex));
        } catch (Exception ex) {
            log.error("Exception occurred while updating profile {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while updating profile info in CvManagerService");
        }
    }

    public APIResponse getDefaultDocs(String userid) {
        try {
            log.info("CvManagerProfileAPIService:getDefaultDocs execution started.");

            String response = restClient
                    .get()
                    .uri(CV_MANAGER_PROFILE_BY_ID_DEFAULT_DOCS, userid)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:getDefaultDocs execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("Exception occurred while fetching defaultDocs {} from CvManagerService, Exception message {}", userid, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching defaultDocs from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("Exception occurred while fetching defaultDocs {} from CvManagerService, Exception message {}", userid, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch defaultDocs info from CvManagerService");
        }
    }

    public APIResponse updateDefaultDocs(String userid, String body) {
        try {
            log.info("CvManagerProfileAPIService:updateDefaultDocs execution started.");
            JsonNode reqBody = parseJson(body);

            String response = restClient
                    .put()
                    .uri(CV_MANAGER_PROFILE_BY_ID_DEFAULT_DOCS, userid)
                    .body(reqBody)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:updateDefaultDocs execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();
        } catch (RestClientResponseException ex) {
            log.error("Exception occurred while updating defaultDocs {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while updating defaultDocs info in CvManagerService", getErrorApiResponse(ex));
        } catch (Exception ex) {
            log.error("Exception occurred while updating defaultDocs {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while updating defaultDocs info in CvManagerService");
        }
    }

    public APIResponse updatePersonalInfo(String userid, String body) {
        try {
            log.info("CvManagerProfileAPIService:updatePersonalInfo execution started.");
            JsonNode reqBody = parseJson(body);

            String response = restClient
                    .put()
                    .uri(CV_MANAGER_PROFILE_BY_ID_PERSONAL_INFO, userid)
                    .body(reqBody)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:updatePersonalInfo execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();
        } catch (RestClientResponseException ex) {
            log.error("Exception occurred while updating personal info {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while updating personal info in CvManagerService", getErrorApiResponse(ex));
        } catch (Exception ex) {
            log.error("Exception occurred while updating personal info {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while updating personal info in CvManagerService");
        }
    }

    public APIResponse updateCareerInfo(String userid, String body) {
        try {
            log.info("CvManagerProfileAPIService:updateCareerInfo execution started.");
            JsonNode reqBody = parseJson(body);

            String response = restClient
                    .put()
                    .uri(CV_MANAGER_PROFILE_BY_ID_CAREER_INFO, userid)
                    .body(reqBody)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:updateCareerInfo execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();
        } catch (RestClientResponseException ex) {
            log.error("Exception occurred while updating career info {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while updating career info in CvManagerService", getErrorApiResponse(ex));
        } catch (Exception ex) {
            log.error("Exception occurred while updating career info {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while updating career info in CvManagerService");
        }
    }

    public APIResponse updateSocialLinks(String userid, String body) {
        try {
            log.info("CvManagerProfileAPIService:updateSocialLinks execution started.");
            JsonNode reqBody = parseJson(body);

            String response = restClient
                    .put()
                    .uri(CV_MANAGER_PROFILE_BY_ID_SOCIAL_LINKS, userid)
                    .body(reqBody)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:updateSocialLinks execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();
        } catch (RestClientResponseException ex) {
            log.error("Exception occurred while updating social links {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while updating social links in CvManagerService", getErrorApiResponse(ex));
        } catch (Exception ex) {
            log.error("Exception occurred while updating social links {} from CvManagerService, Exception message {}", body, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while updating social links in CvManagerService");
        }
    }

    public APIResponse activateProfile(String userid) {
        try {
            log.info("CvManagerProfileAPIService:activateProfile execution started.");

            String response = restClient
                    .put()
                    .uri(CV_MANAGER_PROFILE_BY_ID_ACTIVATE, userid)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:activateProfile execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();
        } catch (RestClientResponseException ex) {
            log.error("Exception occurred while activating profile {} from CvManagerService, Exception message {}", userid, ex.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while activating profile info in CvManagerService", getErrorApiResponse(ex));
        } catch (Exception ex) {
            log.error("Exception occurred while activating profile {} from CvManagerService, Exception message {}", userid, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while activating profile info in CvManagerService");
        }
    }

    public APIResponse deactivateProfile(String userid) {
        try {
            log.info("CvManagerProfileAPIService:deactivateProfile execution started.");

            String response = restClient
                    .put()
                    .uri(CV_MANAGER_PROFILE_BY_ID_DEACTIVATE, userid)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("ProductService:deactivateProfile execution ended.");
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();
        } catch (RestClientResponseException ex) {
            log.error("Exception occurred while deactivating profile {} from CvManagerService, Exception message {}", userid, ex.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while deactivating profile info in CvManagerService", getErrorApiResponse(ex));
        } catch (Exception ex) {
            log.error("Exception occurred while deactivating profile {} from CvManagerService, Exception message {}", userid, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while deactivating profile info in CvManagerService");
        }
    }

    public APIResponse checkProfileAvailability(String userid, String token){
        try {
            log.info("CvManagerProfileAPIService:checkProfileAvailability execution started.");
            JsonNode jwtPayload = extractPayloadFromJwt(token);
            String email = jwtPayload.get("email").asText();
            String firstName = jwtPayload.get("given_name").asText();
            String lastName = jwtPayload.get("family_name").asText();

            RestClient.ResponseSpec response = restClient
                    .post()
                    .uri(CV_MANAGER_PROFILE_BY_ID_CHECK, userid)
                    .body(UserProfileCheckRequestDto.builder()
                            .userId(userid)
                            .email(email)
                            .firstName(firstName)
                            .lastName(lastName)
                            .build())
                    .retrieve();

            log.info("ProductService:checkProfileAvailability execution ended.");
            return APIResponse
                    .builder()
                    .statusCode(response.toBodilessEntity().getStatusCode())
                    .status(ResponseEnum.SUCCESS)
                    .build();
        } catch (RestClientResponseException ex) {
            log.error("Exception occurred while checking profile availability {} from CvManagerService, Exception message {}", userid, ex.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while checking profile availability in CvManagerService", getErrorApiResponse(ex));
        } catch (Exception ex) {
            log.error("Exception occurred while checking profile availability {} from CvManagerService, Exception message {}", userid, ex.getMessage());
            throw new CvManagerServiceException("Exception occurred while checking profile availability in CvManagerService");
        }
    }

    private JsonNode extractPayloadFromJwt(String jwt){
        // split the jwt from "." and decode the second part of the jwt
        String[] jwtParts = jwt.split("\\.");
        String jwtPayload = new String(Base64.getDecoder().decode(jwtParts[1]));
        try {
            return parseJson(jwtPayload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
