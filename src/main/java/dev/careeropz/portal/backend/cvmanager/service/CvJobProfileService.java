package dev.careeropz.portal.backend.cvmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.careeropz.commons.dto.FileDataDto;
import dev.careeropz.commons.fileservice.dto.FileType;
import dev.careeropz.commons.fileservice.dto.responseto.FileUploadResponseDto;
import dev.careeropz.commons.jobprofile.commondto.JobProfileProgressStepDto;
import dev.careeropz.commons.jobprofile.commondto.ProgressUploadsDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static dev.careeropz.portal.backend.cvmanager.controller.CvManagetUrlConstants.*;
import static dev.careeropz.portal.backend.cvmanager.service.CommonFunctions.getErrorApiResponse;
import static dev.careeropz.portal.backend.cvmanager.service.CommonFunctions.parseJson;

@Service
@Slf4j
public class CvJobProfileService {
    private final RestClient restClient;
    private final FileManagerService fileManagerService;

    public CvJobProfileService(ApplicationProperties properties, FileManagerService fileManagerService){
        restClient = RestClient.builder()
                .baseUrl(UriBuilder
                        .builder()
                        .host(properties.getCvManagerHost())
                        .port(properties.getCvManagerPort())
                        .build()
                        .buildHttpUrl())
                .build();

        this.fileManagerService = fileManagerService;
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

    public APIResponse updateJobProfileBasicInfo(String currentUserId, String jobProfileId, String body) {
        try {
            log.info("updateJobProfileBasicInfoById :: jobProfileId:{} :: ENTER", jobProfileId);
            JsonNode reqBody = parseJson(body);

            String response = restClient
                    .put()
                    .uri(CV_MANAGER_JOB_PROFILE_BY_USER_BY_JOB_PROFILE_ID + "/basic-info", currentUserId, jobProfileId)
                    .body(reqBody)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("updateJobProfileBasicInfoById :: jobProfileId:{} :: DONE", jobProfileId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("updateJobProfileBasicInfoById :: jobProfileId:{} :: RestClientResponseException:{}", jobProfileId, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("updateJobProfileBasicInfoById :: jobProfileId:{} :: Exception:{}", jobProfileId, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch profile info from CvManagerService");
        }
    }

    public APIResponse createJobProfileProgressStep(String currentUserId,
                                                    String jobProfileId,
                                                    MultipartFile cv,
                                                    MultipartFile coverLetter,
                                                    List<MultipartFile> otherDocs,
                                                    String data){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JobProfileProgressStepDto progressStepDto = objectMapper.readValue(data, JobProfileProgressStepDto.class);
            progressStepDto.setUploads(ProgressUploadsDto.builder().build());
            fillProgressUploads(currentUserId, cv, coverLetter, otherDocs, progressStepDto);

            String response = restClient
                    .post()
                    .uri(CV_MANAGER_JOB_PROFILE_BY_USER_BY_JOB_PROFILE_ID + "/progress-step", currentUserId, jobProfileId)
                    .body(progressStepDto)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("createJobProfileProgress :: jobProfileId:{} :: DONE", jobProfileId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public APIResponse updateJobProfileProgress(String currentUserId,
                                               String jobProfileId,
                                               String progressId,
                                               MultipartFile cv,
                                               MultipartFile coverLetter,
                                               List<MultipartFile> otherDocs,
                                               String data){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JobProfileProgressStepDto progressStepDto = objectMapper.readValue(data, JobProfileProgressStepDto.class);
            if(progressStepDto.getUploads() != null){
                progressStepDto.setUploads(ProgressUploadsDto.builder().build());
            }
            fillProgressUploads(currentUserId, cv, coverLetter, otherDocs, progressStepDto);

            String response = restClient
                    .put()
                    .uri(CV_MANAGER_JOB_PROFILE_BY_USER_BY_JOB_PROFILE_ID + "/progress/{progress-id}", currentUserId, jobProfileId, progressId)
                    .body(progressStepDto)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("updateJobProfileProgress :: jobProfileId:{} :: DONE", jobProfileId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillProgressUploads(String currentUserId, MultipartFile cv, MultipartFile coverLetter, List<MultipartFile> otherDocs, JobProfileProgressStepDto progressStepDto) {
        if(cv != null){
            FileUploadResponseDto fileUploadResponseDto = fileManagerService.uploadFile(cv, FileType.CV, currentUserId);
            progressStepDto.getUploads().setCv(FileDataDto.builder()
                            .name(fileUploadResponseDto.getFileName())
                            .fileId(fileUploadResponseDto.getFileId())
                            .dateUploaded(fileUploadResponseDto.getUploadedOn().toLocalDate())
                    .build());
        }
        if(coverLetter != null){
            FileUploadResponseDto fileUploadResponseDto = fileManagerService.uploadFile(coverLetter, FileType.COVER_LETTER, currentUserId);
            progressStepDto.getUploads().setCoverLetter(FileDataDto.builder()
                            .name(fileUploadResponseDto.getFileName())
                            .fileId(fileUploadResponseDto.getFileId())
                            .dateUploaded(fileUploadResponseDto.getUploadedOn().toLocalDate())
                    .build());
        }
        if(otherDocs != null){
            for(MultipartFile file: otherDocs){
                FileUploadResponseDto fileUploadResponseDto = fileManagerService.uploadFile(file, FileType.OTHER, currentUserId);
                progressStepDto.getUploads().getOther().add(FileDataDto.builder()
                        .name(fileUploadResponseDto.getFileName())
                        .fileId(fileUploadResponseDto.getFileId())
                        .dateUploaded(fileUploadResponseDto.getUploadedOn().toLocalDate())
                        .build());
            }
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

    public APIResponse getJobProfileSuggestions(String currentUserId) {
        try {
            log.info("getJobProfileSuggestions :: userid:{} :: ENTER", currentUserId);

            String response = restClient
                    .get()
                    .uri(CV_MANAGER_JOB_PROFILE_SUGGESTIONS_BY_USER_ID, currentUserId)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("getJobProfileSuggestions :: userid:{} :: DONE", currentUserId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("getJobProfileSuggestions :: userid:{} :: RestClientResponseException:{}", currentUserId, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("getJobProfileSuggestions :: userid:{} :: Exception:{}", currentUserId, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch profile info from CvManagerService");
        }
    }

    public APIResponse postJobProfileSuggestions(String currentUserId, String body) {
        try {
            log.info("postJobProfileSuggestions :: userid:{} :: ENTER", currentUserId);
            JsonNode reqBody = parseJson(body);

            String response = restClient
                    .post()
                    .uri(CV_MANAGER_JOB_PROFILE_SUGGESTIONS_BY_USER_ID, currentUserId)
                    .body(reqBody)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("postJobProfileSuggestions :: userid:{} :: DONE", currentUserId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("postJobProfileSuggestions :: userid:{} :: RestClientResponseException:{}", currentUserId, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("postJobProfileSuggestions :: userid:{} :: Exception:{}", currentUserId, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch profile info from CvManagerService");
        }
    }

    public APIResponse deleteJobProfileSuggestions(String currentUserId, String suggestionId) {
        try {
            log.info("deleteJobProfileSuggestions :: userid:{} :: ENTER", currentUserId);

            String response = restClient
                    .delete()
                    .uri(CV_MANAGER_JOB_PROFILE_SUGGESTIONS_BY_USER_BY_SUGGESTION_ID, currentUserId, suggestionId)
                    .retrieve()
                    .body(String.class);

            JsonNode jsonNode = parseJson(response);
            log.info("deleteJobProfileSuggestions :: userid:{} :: DONE", currentUserId);
            return APIResponse
                    .builder()
                    .status(ResponseEnum.SUCCESS)
                    .result(jsonNode)
                    .build();

        } catch (RestClientResponseException restEx) {
            log.error("deleteJobProfileSuggestions :: userid:{} :: RestClientResponseException:{}", currentUserId, restEx.getMessage());
            throw new CvManagerServiceException("Status %s error occurred while fetching profile from cv manager server", getErrorApiResponse(restEx));
        } catch (Exception ex) {
            log.error("deleteJobProfileSuggestions :: userid:{} :: Exception:{}", currentUserId, ex.getMessage());
            throw new CvManagerServiceException("Error occurred while fetch profile info from CvManagerService");
        }
    }
}
