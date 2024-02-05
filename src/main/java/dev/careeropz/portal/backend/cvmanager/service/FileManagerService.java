package dev.careeropz.portal.backend.cvmanager.service;

import dev.careeropz.commons.fileservice.dto.FileType;
import dev.careeropz.commons.fileservice.dto.requestdto.FileUploadRequestDto;
import dev.careeropz.commons.fileservice.dto.responseto.FileUploadResponseDto;
import dev.careeropz.portal.backend.config.ApplicationProperties;
import dev.careeropz.portal.backend.cvmanager.dto.APIResponse;
import dev.careeropz.portal.backend.util.UriBuilder;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

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
        FileUploadRequestDto fileUploadRequestDto = FileUploadRequestDto.builder()
                .fileType(fileType)
                .userId(userId)
                .file(file)
                .build();

        return restClient
                .post()
                .uri("/file-manager/api/v1/file")
                .body(fileUploadRequestDto)
                .retrieve()
                .body(FileUploadResponseDto.class);
    }
}
