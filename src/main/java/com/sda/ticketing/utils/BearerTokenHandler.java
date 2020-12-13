package com.sda.ticketing.utils;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

@Component
public class BearerTokenHandler extends DefaultAccessTokenConverter{

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication auth2Authentication =  super.extractAuthentication(map);
        auth2Authentication.setDetails(map);
        return auth2Authentication;
    }
}
