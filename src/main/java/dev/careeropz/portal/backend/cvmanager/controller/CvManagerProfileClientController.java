package dev.careeropz.portal.backend.cvmanager.controller;

import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.service.CvManagerProfileClientService;
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
    private final CvManagerProfileClientService cvManagerProfileClientService;

    @Tag(name= "User Profile", description = "User profile related APIs")
    @GetMapping(USER_PROFILE + PARAM_USER_ID)
    public ResponseEntity<APIResponse> getProfileInfo(@PathVariable String userid) {

        log.info("CvManagerProfileController::getProfileInfo userid {}", userid);
        APIResponse response = cvManagerProfileClientService.getProfileById(userid);
        log.info("ProductController::getProfileInfo response {}", userid);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Tag(name= "User Profile")
    @PostMapping(USER_PROFILE)
    public ResponseEntity<APIResponse> createProfileInfo(@RequestBody String body) {

        log.info("CvManagerProfileController::createProfileInfo body {}", body);
        APIResponse apiResponse = cvManagerProfileClientService.createProfile(body);
        log.info("ProductController::createProfileInfo response {}", body);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "User Profile")
    @PutMapping(USER_PROFILE + PARAM_USER_ID)
    public ResponseEntity<APIResponse> updateProfileInfo(@PathVariable String userid, @RequestBody String body) {

        log.info("CvManagerProfileController::updateProfileInfo user-id {}", userid);
        APIResponse apiResponse = cvManagerProfileClientService.updateProfileById(userid, body);
        log.info("ProductController::updateProfileInfo response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Default Docs", description = "Default docs related APIs")
    @GetMapping(USER_PROFILE + PARAM_USER_ID + DEFAULT_DOCS)
    public ResponseEntity<APIResponse> getDefaultDocs(@PathVariable String userid) {

        log.info("CvManagerProfileController::getDefaultDocs user-id {}", userid);
        APIResponse apiResponse = cvManagerProfileClientService.getDefaultDocs(userid);
        log.info("ProductController::getDefaultDocs response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Default Docs")
    @PutMapping(USER_PROFILE + PARAM_USER_ID + DEFAULT_DOCS)
    public ResponseEntity<APIResponse> updateDefaultDocs(@PathVariable String userid, @RequestBody String body) {

        log.info("CvManagerProfileController::updateDefaultDocs user-id {}", userid);
        APIResponse apiResponse = cvManagerProfileClientService.updateDefaultDocs(userid, body);
        log.info("ProductController::updateDefaultDocs response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Profile activation", description = "Profile activation related APIs")
    @PutMapping(USER_PROFILE + PARAM_USER_ID + ACTIVATE)
    public ResponseEntity<APIResponse> activateProfile(@PathVariable String userid) {

        log.info("CvManagerProfileController::activateProfile user-id {}", userid);
        APIResponse apiResponse = cvManagerProfileClientService.activateProfile(userid);
        log.info("ProductController::activateProfile response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Tag(name= "Profile activation")
    @PutMapping(USER_PROFILE + PARAM_USER_ID + DEACTIVATE)
    public ResponseEntity<APIResponse> deactivateProfile(@PathVariable String userid) {

        log.info("CvManagerProfileController::deactivateProfile user-id {}", userid);
        APIResponse apiResponse = cvManagerProfileClientService.deactivateProfile(userid);
        log.info("ProductController::deactivateProfile response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

}
