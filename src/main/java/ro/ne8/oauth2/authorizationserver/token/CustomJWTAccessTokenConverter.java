package ro.ne8.oauth2.authorizationserver.token;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Collections;
import java.util.Map;
import java.util.Set;


public class CustomJWTAccessTokenConverter extends JwtAccessTokenConverter {

    private final JsonParser objectMapper = JsonParserFactory.create();

    @Override
    public OAuth2AccessToken enhance(final OAuth2AccessToken accessToken, final OAuth2Authentication authentication) {
        final DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);

        result.setValue(this.encode(result, authentication));
        result.setScope(this.getEmptyScope());
        result.setAdditionalInformation(this.getEmptyAdditionalInformation());
        return result;
    }

    private Set<String> getEmptyScope() {
        return Collections.emptySet();
    }

    private Map<String, Object> getEmptyAdditionalInformation() {
        return Collections.emptyMap();
    }
}
