package dev.careeropz.portal.backend.cvmanager.controller;

import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.dto.pagination.CommonPaginationRequest;
import dev.careeropz.portal.backend.cvmanager.service.CvJobProfileService;
import dev.careeropz.portal.backend.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.careeropz.portal.backend.cvmanager.controller.CvManagetUrlConstants.*;

@RestController
@RequestMapping(CV_MANAGER_API + JOB_PROFILE)
@Slf4j
@RequiredArgsConstructor
public class CvManagerJobProfileClientController {
    private final CvJobProfileService cvJobProfileService;

    @Tag(name= "Job Profile", description = "Job profile related APIs")
    @GetMapping(PARAM_JOB_PROFILE_ID)
    public ResponseEntity<APIResponse> getJobProfileById(@RequestHeader(name = "Authorization", required = false) String token,
                                                         @PathVariable(name = PARAM_JOB_PROFILE_ID) String jobProfileId) {
        String currentUserId = JwtUtil.extractUserId(token);
        log.info("getJobProfileById :: jobProfileId:{} :: ENTER", jobProfileId);
        APIResponse response = cvJobProfileService.getJobProfileById(currentUserId, jobProfileId);
        log.info("getJobProfileById :: jobProfileId:{} :: DONE", jobProfileId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Tag(name= "Job Profile")
    @PostMapping()
    public ResponseEntity<APIResponse> createJobProfile(@RequestHeader(name = "Authorization", required = false) String token,
                                                        @RequestBody String body) {

        String currentUserId = JwtUtil.extractUserId(token);
        log.info("createJobProfileInfo :: ENTER");
        APIResponse apiResponse = cvJobProfileService.createJobProfile(currentUserId, body);
        log.info("createJobProfileInfo :: DONE");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Tag(name= "Job Profile")
    @PutMapping(PARAM_JOB_PROFILE_ID)
    public ResponseEntity<APIResponse> updateJobProfile(@RequestHeader(name = "Authorization", required = false) String token,
                                                        @PathVariable(name = PARAM_JOB_PROFILE_ID) String jobProfileId,
                                                        @RequestBody String body) {
        String currentUserId = JwtUtil.extractUserId(token);
        log.info("updateJobProfileInfo :: job-profile-id:{} :: ENTER", jobProfileId);
        APIResponse apiResponse = cvJobProfileService.updateJobProfileById(currentUserId, jobProfileId, body);
        log.info("updateJobProfileInfo :: job-profile-id:{} :: DONE", jobProfileId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Job Profile")
    @DeleteMapping(PARAM_JOB_PROFILE_ID)
    public ResponseEntity<APIResponse> deleteJobProfile(@RequestHeader(name = "Authorization", required = false) String token,
                                                        @PathVariable(name = PARAM_JOB_PROFILE_ID) String jobProfileId) {
        String currentUserId = JwtUtil.extractUserId(token);
        log.info("deleteJobProfileInfo :: job-profile-id:{} :: ENTER", jobProfileId);
        APIResponse apiResponse = cvJobProfileService.deleteJobProfileById(currentUserId, jobProfileId);
        log.info("deleteJobProfileInfo :: job-profile-id:{} :: DONE", jobProfileId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Job Profile")
    @GetMapping()
    public ResponseEntity<APIResponse> getJobProfilesByUser(@RequestHeader(name = "Authorization", required = false) String token,
                                                            @RequestParam(value = "page-no", defaultValue = "0") int pageNo,
                                                            @RequestParam(value = "page-size", defaultValue = "10") int pageSize,
                                                            @RequestParam(value = "sort-by", defaultValue = "id") String sortBy,
                                                            @RequestParam(value = "sort-direction", defaultValue = "asc") String sortDirection) {
        CommonPaginationRequest paginationRequest = new CommonPaginationRequest(pageNo, pageSize, sortBy, sortDirection);
        String currentUserId = JwtUtil.extractUserId(token);
        log.info("getJobProfilesByUser :: ENTER");
        APIResponse apiResponse = cvJobProfileService.getJobProfilesByUser(currentUserId, paginationRequest);
        log.info("getJobProfilesByUser :: DONE");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}
