package mx.bluelight.yelpcamp.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpringDocConfig {

    @Bean
    public OpenAPI yelpCampApi() {
        return new OpenAPI()
            .info(new Info().title("YelpCamp API")
                .description("YelCamp restful api")
                .version("v0.0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
