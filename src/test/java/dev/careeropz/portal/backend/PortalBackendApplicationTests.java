package dev.careeropz.portal.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.codec.multipart.Part;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@SpringBootTest
class PortalBackendApplicationTests {

	@Test
	void contextLoads() {
		// create a miltipart file

		MultipartFile file = null;
		// read a file from the resources folder
		File file1 = new File("src/main/resources/somefile.txt");

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();

		parts.add("file", file1);
		parts.add("fileType", "CV");
		parts.add("userId", "1");

		// create a rest template and upload the file
		RestTemplate restTemplate = new RestTemplate();

		String serverUrl = "http://localhost:9093/file-manager/api/v1/file";

		restTemplate.postForEntity(serverUrl, parts, String.class);

	}

}
