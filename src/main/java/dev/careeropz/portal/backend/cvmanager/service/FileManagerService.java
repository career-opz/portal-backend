package dev.careeropz.portal.backend.cvmanager.service;

import dev.careeropz.commons.fileservice.dto.FileType;
import dev.careeropz.commons.fileservice.dto.requestdto.FileUploadRequestDto;
import dev.careeropz.commons.fileservice.dto.responseto.FileUploadResponseDto;
import dev.careeropz.portal.backend.config.ApplicationProperties;
import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.util.UriBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileManagerService {

    private final RestClient restClient;

    public FileManagerService(ApplicationProperties applicationProperties){
        this.restClient = RestClient.builder()
                .baseUrl(UriBuilder
                        .builder()
                        .host(applicationProperties.getFileServerHost())
                        .port(applicationProperties.getFileServerPort())
                        .build()
                        .buildHttpUrl())
                .build();
    }
    public FileUploadResponseDto uploadFile(MultipartFile file, FileType fileType, String userId){
        try{
            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("fileType", fileType.toString());
            parts.add("userId", userId);

            parts.add("file", new HttpEntity<>(file.getBytes(), new HttpHeaders()));
            parts.add("fileName", file.getOriginalFilename());

            RestTemplate restTemplate = new RestTemplate();
            String serverUrl = "http://localhost:9093/file-manager/api/v1/file";

            return restTemplate.postForEntity(serverUrl, parts, FileUploadResponseDto.class).getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
