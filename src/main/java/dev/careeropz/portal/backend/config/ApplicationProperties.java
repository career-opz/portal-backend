package dev.careeropz.portal.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "services")
public class ApplicationProperties {
    String cvManagerHost;
    Integer cvManagerPort;
}