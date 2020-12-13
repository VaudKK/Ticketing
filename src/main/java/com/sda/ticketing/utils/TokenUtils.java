package com.sda.ticketing.utils;

import io.jsonwebtoken.lang.Assert;
import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    private Map<String,Object> claims = new HashMap<>();

    private Authentication authentication = null;

    public void initialize(Authentication authentication){
        this.authentication = authentication;
        if(authentication.getPrincipal() instanceof KeycloakPrincipal){
            KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) authentication.getPrincipal();
            claims = keycloakPrincipal.getKeycloakSecurityContext().getToken().getOtherClaims();
        }
    }

    public String getPhoneNumber(){
        Assert.notNull(authentication);
        return (String) getClaimByKey("user_name");
    }

    private Object getClaimByKey(String key){
        Assert.notNull(authentication);
        return claims.get(key);
    }

}
