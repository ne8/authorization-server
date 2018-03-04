package ro.ne8.oauth2.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return
                new Docket(DocumentationType.SWAGGER_2)
                        .groupName("user-handler")
                        .select()
                        .apis(RequestHandlerSelectors.basePackage(
                                "ro.ne8.oauth2.authorizationserver.controller"
                        ))
                        .paths(PathSelectors.any())
                        .build()
                        .apiInfo(this.apiInfo("User Handler API", "User handler"));
    }

    private ApiInfo apiInfo(final String title, final String description) {
        final Contact contact = new Contact("Catalin Patrut", "",
                "cpatrut@outlook.com");
        return new ApiInfo(title, description, "1.0", "terms of controller url",
                contact, "license", "license url");
    }
}
