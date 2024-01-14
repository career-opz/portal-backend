package dev.careeropz.portal.backend.cvmanager.service;

import com.fasterxml.jackson.databind.JsonNode;
import dev.careeropz.portal.backend.config.ApplicationProperties;
import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.dto.ResponseEnum;
import dev.careeropz.portal.backend.cvmanager.dto.pagination.CommonPaginationRequest;
import dev.careeropz.portal.backend.cvmanager.exception.CvManagerServiceException;
import dev.careeropz.portal.backend.util.UriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import static dev.careeropz.portal.backend.cvmanager.controller.CvManagetUrlConstants.*;
import static dev.careeropz.portal.backend.cvmanager.service.CommonFunctions.getErrorApiResponse;
import static dev.careeropz.portal.backend.cvmanager.service.CommonFunctions.parseJson;

@Service
@Slf4j
public class CvJobProfileService {
    private final RestClient restClient;

    public CvJobProfileService(ApplicationProperties properties){
        restClient = RestClient.builder()
                .baseUrl(UriBuilder
                        .builder()
                        .host(properties.getCvManagerHost())
                        .port(properties.getCvManagerPort())
                        .build()
                        .buildHttpUrl())
                .build();
    }

    public APIResponse getJobProfileById(String currentUserId, String jobProfileId) {
        try {
            log.info("getProfileById :: userid:{} :: ENTER", jobProfileId);

            String response = restClient
                    .get()
                    .uri(CV_MANAGER_JOB_PROFILE_BY_USER_BY_JOB_PROFILE_ID, currentUserId, jobProfileId)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("getProfileById :: userid:{} :: DONE", jobProfileId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("getJobProfileById :: userid:{} :: RestClientResponseException:{}", jobProfileId, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("getJobProfileById :: userid:{} :: Exception:{}", jobProfileId, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch job profile from CvManagerService");
        }
    }

    public APIResponse createJobProfile(String currentUserId, String body) {
        try {
            log.info("createJobProfile :: userid:{} :: ENTER", currentUserId);
            JsonNode reqBody = parseJson(body);

            String response = restClient
                    .post()
                    .uri(CV_MANAGER_JOB_PROFILE_BY_USER_ID, currentUserId)
                    .body(reqBody)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("createJobProfile :: userid:{} :: DONE", currentUserId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("createJobProfile :: userid:{} :: RestClientResponseException:{}", currentUserId, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("createJobProfile :: userid:{} :: Exception:{}", currentUserId, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch profile info from CvManagerService");
        }
    }

    public APIResponse updateJobProfileById(String currentUserId, String jobProfileId, String body) {
        try {
            log.info("updateJobProfileById :: jobProfileId:{} :: ENTER", jobProfileId);
            JsonNode reqBody = parseJson(body);

            String response = restClient
                    .put()
                    .uri(CV_MANAGER_JOB_PROFILE_BY_USER_BY_JOB_PROFILE_ID, currentUserId, jobProfileId)
                    .body(reqBody)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("updateJobProfileById :: jobProfileId:{} :: DONE", jobProfileId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("updateJobProfileById :: jobProfileId:{} :: RestClientResponseException:{}", jobProfileId, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("updateJobProfileById :: jobProfileId:{} :: Exception:{}", jobProfileId, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch profile info from CvManagerService");
        }
    }

    public APIResponse deleteJobProfileById(String currentUserId, String jobProfileId) {
        try {
            log.info("deleteJobProfileById :: jobProfileId:{} :: ENTER", jobProfileId);

            String response = restClient
                    .delete()
                    .uri(CV_MANAGER_JOB_PROFILE_BY_USER_BY_JOB_PROFILE_ID, currentUserId, jobProfileId)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("deleteJobProfileById :: jobProfileId:{} :: DONE", jobProfileId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("deleteJobProfileById :: jobProfileId:{} :: RestClientResponseException:{}", jobProfileId, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("deleteJobProfileById :: jobProfileId:{} :: Exception:{}", jobProfileId, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch profile info from CvManagerService");
        }
    }

    public APIResponse getJobProfilesByUser(String currentUserId, CommonPaginationRequest paginationRequest) {
        try {
            log.info("getJobProfilesByUser :: userid:{} :: ENTER", currentUserId);

            String response = restClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(CV_MANAGER_JOB_PROFILE_BY_USER_ID)
                            .queryParam("page-no", paginationRequest.getPageNo())
                            .queryParam("page-size", paginationRequest.getPageSize())
                            .queryParam("sort-by", paginationRequest.getSortBy())
                            .queryParam("sort-direction", paginationRequest.getSortDirection())
                            .build(currentUserId))
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("getJobProfilesByUser :: userid:{} :: DONE", currentUserId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("getJobProfilesByUser :: userid:{} :: RestClientResponseException:{}", currentUserId, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("getJobProfilesByUser :: userid:{} :: Exception:{}", currentUserId, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch profile info from CvManagerService");
        }
    }
}
