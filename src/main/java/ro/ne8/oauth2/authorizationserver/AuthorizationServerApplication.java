package ro.ne8.oauth2.authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ro.ne8.oauth2.authorizationserver")
public class AuthorizationServerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);

    }
}
