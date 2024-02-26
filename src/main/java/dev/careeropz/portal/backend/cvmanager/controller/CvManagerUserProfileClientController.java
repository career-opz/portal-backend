package dev.careeropz.portal.backend.cvmanager.controller;

import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.service.CvUserProfileService;
import dev.careeropz.portal.backend.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.careeropz.portal.backend.cvmanager.controller.CvManagetUrlConstants.*;


@RestController
@RequestMapping(CV_MANAGER_PROFILE)
@Slf4j
@RequiredArgsConstructor
public class CvManagerUserProfileClientController {
    private final CvUserProfileService cvUserProfileService;

    @Tag(name= "User Profile", description = "User profile related APIs")
    @GetMapping()
    public ResponseEntity<APIResponse> getProfileInfo(@RequestHeader(name = "Authorization", required = false) String token) {
        String currentUserId = JwtUtil.extractUserId(token);
        log.info("CvManagerProfileController::getProfileInfo userid {}", currentUserId);
        APIResponse response = cvUserProfileService.getProfileById(currentUserId);
        log.info("ProductController::getProfileInfo response {}", currentUserId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Tag(name= "User Profile")
    @PostMapping
    public ResponseEntity<APIResponse> createProfileInfo(@RequestBody String body) {

        log.info("CvManagerProfileController::createProfileInfo :: ENTER");
        APIResponse apiResponse = cvUserProfileService.createProfile(body);
        log.info("ProductController::createProfileInfo :: DONE");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "User Profile")
    @PutMapping()
    public ResponseEntity<APIResponse> updateProfileInfo(@RequestHeader(name = "Authorization", required = false) String token,
                                                         @RequestBody String body) {
        String currentUserId = JwtUtil.extractUserId(token);
        log.info("CvManagerProfileController::updateProfileInfo user-id {}", currentUserId);
        APIResponse apiResponse = cvUserProfileService.updateProfileById(currentUserId, body);
        log.info("ProductController::updateProfileInfo response {}", currentUserId);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Default Docs", description = "Default docs related APIs")
    @GetMapping(DEFAULT_DOCS)
    public ResponseEntity<APIResponse> getDefaultDocs(@RequestHeader(name = "Authorization", required = false) String token) {

        String currentUserId = JwtUtil.extractUserId(token);
        log.info("CvManagerProfileController::getDefaultDocs user-id {}", currentUserId);
        APIResponse apiResponse = cvUserProfileService.getDefaultDocs(currentUserId);
        log.info("ProductController::getDefaultDocs response {}", currentUserId);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Default Docs")
    @PutMapping(DEFAULT_DOCS)
    public ResponseEntity<APIResponse> updateDefaultDocs(@RequestHeader(name = "Authorization", required = false) String token,
                                                         @RequestBody String body) {
        String currentUserId = JwtUtil.extractUserId(token);
        log.info("CvManagerProfileController::updateDefaultDocs user-id {}", currentUserId);
        APIResponse apiResponse = cvUserProfileService.updateDefaultDocs(currentUserId, body);
        log.info("ProductController::updateDefaultDocs response {}", currentUserId);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Personal Info", description = "Personal info related APIs")
    @PutMapping(PERSONAL_INFO)
    public ResponseEntity<APIResponse> updatePersonalInfo(@RequestHeader(name = "Authorization", required = false) String token,
                                                          @RequestBody String body) {
            String currentUserId = JwtUtil.extractUserId(token);
            log.info("CvManagerProfileController::updatePersonalInfo user-id {}", currentUserId);
            APIResponse apiResponse = cvUserProfileService.updatePersonalInfo(currentUserId, body);
            log.info("ProductController::updatePersonalInfo response {}", currentUserId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Tag(name= "Career Info", description = "Career info related APIs")
    @PutMapping(CAREER_INFO)
    public ResponseEntity<APIResponse> updateCareerInfo(@RequestHeader(name = "Authorization", required = false) String token,
                                                        @RequestBody String body) {
            String currentUserId = JwtUtil.extractUserId(token);
            log.info("CvManagerProfileController::updateCareerInfo user-id {}", currentUserId);
            APIResponse apiResponse = cvUserProfileService.updateCareerInfo(currentUserId, body);
            log.info("ProductController::updateCareerInfo response {}", currentUserId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Tag(name= "Social Links", description = "Social links related APIs")
    @PutMapping(SOCIAL_LINKS)
    public ResponseEntity<APIResponse> updateSocialLinks(@RequestHeader(name = "Authorization", required = false) String token,
                                                         @RequestBody String body) {
            String currentUserId = JwtUtil.extractUserId(token);
            log.info("CvManagerProfileController::updateSocialLinks user-id {}", currentUserId);
            APIResponse apiResponse = cvUserProfileService.updateSocialLinks(currentUserId, body);
            log.info("ProductController::updateSocialLinks response {}", currentUserId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Tag(name= "Profile activation", description = "Profile activation related APIs")
    @PutMapping(PARAM_USER_ID + ACTIVATE)
    public ResponseEntity<APIResponse> activateProfile(@RequestHeader(name = "Authorization", required = false) String token,
                                                       @PathVariable String currentUserId) {

        log.info("CvManagerProfileController::activateProfile user-id {}", currentUserId);
        APIResponse apiResponse = cvUserProfileService.activateProfile(currentUserId);
        log.info("ProductController::activateProfile response {}", currentUserId);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Profile activation")
    @PutMapping(PARAM_USER_ID + DEACTIVATE)
    public ResponseEntity<APIResponse> deactivateProfile(@RequestHeader(name = "Authorization", required = false) String token,
                                                         @PathVariable String userid) {

        log.info("CvManagerProfileController::deactivateProfile user-id {}", userid);
        APIResponse apiResponse = cvUserProfileService.deactivateProfile(userid);
        log.info("ProductController::deactivateProfile response {}", userid);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @Tag(name= "Check profile availability")
    @GetMapping(CHECK)
    public ResponseEntity<APIResponse> checkProfileAvailability(@RequestHeader(name="Authorization", required = false) String token){
        String currentUserId = JwtUtil.extractUserId(token);
        log.info("CvManagerProfileController::checkProfileAvailability user-id {}", currentUserId);
        APIResponse apiResponse = cvUserProfileService.checkProfileAvailability(currentUserId, token);
        log.info("ProductController::checkProfileAvailability response {}", currentUserId);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }
}
