package ro.ne8.oauth2.authorizationserver.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "ro.ne8.oauth2.authorizationserver")
@EnableJpaRepositories(basePackages = {"ro.ne8.oauth2.authorizationserver.repositories"})
@EntityScan(basePackages = {"ro.ne8.oauth2.authorizationserver.entities"})
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
