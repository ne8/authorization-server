package ro.ne8.oauth2.authorizationserver.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@ApiIgnore
public class LandingController {

    @RequestMapping("/")
    public void home(final HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
