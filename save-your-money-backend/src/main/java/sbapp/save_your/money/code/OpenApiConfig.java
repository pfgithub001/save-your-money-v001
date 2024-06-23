package sbapp.save_your.money.code;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class OpenApiConfig{

    /**
     * This method is used to create the openapi object.
     * @return the openapi object
     */
    @Bean
    public OpenAPI openApi() {
        log.info("init openapiconfig");
        return new OpenAPI().specVersion(SpecVersion.V30).openapi("3.0.3")
                .info(new Info()
                        .title("Save Your Money-API")
                        .description("Save your money-API")
                        .version("1.0.0")
                        .summary("A Save your money API")
                );
    }
    
}