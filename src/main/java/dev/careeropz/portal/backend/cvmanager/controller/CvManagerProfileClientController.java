package dev.careeropz.portal.backend.cvmanager.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.cvmanager.service.CvManagerProfileClientService;
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
    @GetMapping(USER_PROFILE + PARAM_USER_ID)
    public ResponseEntity<APIResponse> getProfileInfo(@PathVariable String userid) {

        log.info("CvManagerProfileController::getProfileInfo userid {}", userid);
        APIResponse response = cvManagerProfileClientService.getProfileById(userid);
        log.info("ProductController::getProfileInfo response {}", userid);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(USER_PROFILE + PARAM_USER_ID)
    public ResponseEntity<APIResponse> updateProfileInfo(@PathVariable String userid, @RequestBody String body) {

        log.info("CvManagerProfileController::updateProfileInfo user-id {}", userid);
        APIResponse apiResponse = cvManagerProfileClientService.updateProfileById(userid, body);
        log.info("ProductController::updateProfileInfo response {}", userid);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

}
