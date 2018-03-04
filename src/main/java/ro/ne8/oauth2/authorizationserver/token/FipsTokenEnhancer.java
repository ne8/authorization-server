package ro.ne8.oauth2.authorizationserver.token;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import ro.ne8.oauth2.authorizationserver.service.util.DRBGGeneratorBCFipsUtil;

public class FipsTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(final OAuth2AccessToken accessToken, final OAuth2Authentication authentication) {
        ((DefaultOAuth2AccessToken) accessToken).setValue(DRBGGeneratorBCFipsUtil.generateSHA512RandomString());
        ((DefaultOAuth2AccessToken) accessToken).setRefreshToken(new DefaultOAuth2RefreshToken(DRBGGeneratorBCFipsUtil.generateSHA512RandomString()));
        return accessToken;
    }
}
