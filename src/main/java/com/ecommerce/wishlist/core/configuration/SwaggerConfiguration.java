package com.ecommerce.wishlist.core.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("wishlist-api")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI appOpenAPI(BuildProperties buildProperties) {
        Info info = new Info()
                .title(buildProperties.get("title"))
                .description(buildProperties.get("description"))
                .version(buildProperties.getVersion());

        return new OpenAPI().info(info);
    }
}
