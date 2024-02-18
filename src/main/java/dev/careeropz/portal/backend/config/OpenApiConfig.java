package dev.careeropz.portal.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "CareerOpz Portal Backend APIs",
                version = "1.0",
                description = "CareerOpz Portal Backend APIs"
        ),
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.OPENIDCONNECT,
        description = "Authentication needed to access the APIs",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER,
        openIdConnectUrl = "http://localhost:7070/realms/cv-manager/.well-known/openid-configuration"
)
public class OpenApiConfig {
}
