package dev.careeropz.portal.backend.cvmanager.controller;

import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.service.CvUserProfileService;
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
public class CvManagerProfileClientController {
    private final CvUserProfileService cvUserProfileService;

    @Tag(name= "User Profile", description = "User profile related APIs")
    @GetMapping(USER_PROFILE + PARAM_USER_ID)
    public ResponseEntity<APIResponse> getProfileInfo(@RequestHeader(name = "Authorization", required = false) String authorization, @PathVariable String userid) {

        log.info("CvManagerProfileController::getProfileInfo userid {}", userid);

        APIResponse response = cvUserProfileService.getProfileById(userid);
        log.info("ProductController::getProfileInfo response {}", userid);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Tag(name= "User Profile")
    @PostMapping(USER_PROFILE)
    public ResponseEntity<APIResponse> createProfileInfo(@RequestBody String body) {

        log.info("CvManagerProfileController::createProfileInfo :: ENTER");
        APIResponse apiResponse = cvUserProfileService.createProfile(body);
        log.info("ProductController::createProfileInfo :: DONE");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "User Profile")
    @PutMapping(USER_PROFILE + PARAM_USER_ID)
    public ResponseEntity<APIResponse> updateProfileInfo(@RequestHeader(name = "Authorization", required = false) String authorization, @PathVariable String userid, @RequestBody String body) {

        log.info("CvManagerProfileController::updateProfileInfo user-id {}", userid);
        APIResponse apiResponse = cvUserProfileService.updateProfileById(userid, body);
        log.info("ProductController::updateProfileInfo response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Default Docs", description = "Default docs related APIs")
    @GetMapping(USER_PROFILE + PARAM_USER_ID + DEFAULT_DOCS)
    public ResponseEntity<APIResponse> getDefaultDocs(@RequestHeader(name = "Authorization", required = false) String authorization, @PathVariable String userid) {

        log.info("CvManagerProfileController::getDefaultDocs user-id {}", userid);
        APIResponse apiResponse = cvUserProfileService.getDefaultDocs(userid);
        log.info("ProductController::getDefaultDocs response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Default Docs")
    @PutMapping(USER_PROFILE + PARAM_USER_ID + DEFAULT_DOCS)
    public ResponseEntity<APIResponse> updateDefaultDocs(@RequestHeader(name = "Authorization", required = false) String authorization, @PathVariable String userid, @RequestBody String body) {

        log.info("CvManagerProfileController::updateDefaultDocs user-id {}", userid);
        APIResponse apiResponse = cvUserProfileService.updateDefaultDocs(userid, body);
        log.info("ProductController::updateDefaultDocs response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Personal Info", description = "Personal info related APIs")
    @PutMapping(USER_PROFILE + PARAM_USER_ID + PERSONAL_INFO)
    public ResponseEntity<APIResponse> updatePersonalInfo(@RequestHeader(name = "Authorization", required = false) String authorization, @PathVariable String userid, @RequestBody String body) {

            log.info("CvManagerProfileController::updatePersonalInfo user-id {}", userid);
            APIResponse apiResponse = cvUserProfileService.updatePersonalInfo(userid, body);
            log.info("ProductController::updatePersonalInfo response {}", userid);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Tag(name= "Career Info", description = "Career info related APIs")
    @PutMapping(USER_PROFILE + PARAM_USER_ID + CAREER_INFO)
    public ResponseEntity<APIResponse> updateCareerInfo(@RequestHeader(name = "Authorization", required = false) String authorization, @PathVariable String userid, @RequestBody String body) {

            log.info("CvManagerProfileController::updateCareerInfo user-id {}", userid);
            APIResponse apiResponse = cvUserProfileService.updateCareerInfo(userid, body);
            log.info("ProductController::updateCareerInfo response {}", userid);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Tag(name= "Social Links", description = "Social links related APIs")
    @PutMapping(USER_PROFILE + PARAM_USER_ID + SOCIAL_LINKS)
    public ResponseEntity<APIResponse> updateSocialLinks(@RequestHeader(name = "Authorization", required = false) String authorization, @PathVariable String userid, @RequestBody String body) {

            log.info("CvManagerProfileController::updateSocialLinks user-id {}", userid);
            APIResponse apiResponse = cvUserProfileService.updateSocialLinks(userid, body);
            log.info("ProductController::updateSocialLinks response {}", userid);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Tag(name= "Profile activation", description = "Profile activation related APIs")
    @PutMapping(USER_PROFILE + PARAM_USER_ID + ACTIVATE)
    public ResponseEntity<APIResponse> activateProfile(@RequestHeader(name = "Authorization", required = false) String authorization, @PathVariable String userid) {

        log.info("CvManagerProfileController::activateProfile user-id {}", userid);
        APIResponse apiResponse = cvUserProfileService.activateProfile(userid);
        log.info("ProductController::activateProfile response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Profile activation")
    @PutMapping(USER_PROFILE + PARAM_USER_ID + DEACTIVATE)
    public ResponseEntity<APIResponse> deactivateProfile(@RequestHeader(name = "Authorization", required = false) String authorization, @PathVariable String userid) {

        log.info("CvManagerProfileController::deactivateProfile user-id {}", userid);
        APIResponse apiResponse = cvUserProfileService.deactivateProfile(userid);
        log.info("ProductController::deactivateProfile response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

}
