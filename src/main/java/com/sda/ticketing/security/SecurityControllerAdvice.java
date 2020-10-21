package com.sda.ticketing.security;

import com.sda.ticketing.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.reactive.result.view.CsrfRequestDataValueProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;

@ControllerAdvice
public class SecurityControllerAdvice {

    @ModelAttribute
    Mono<CsrfToken> csrfToken(ServerWebExchange webExchange){
        Mono<CsrfToken> csrfToken = webExchange.getAttribute(CsrfToken.class.getName());
        if(csrfToken == null){
            return Mono.empty();
        }

        return csrfToken.doOnSuccess(token -> webExchange.getAttributes()
        .put(CsrfRequestDataValueProcessor.DEFAULT_CSRF_ATTR_NAME,token));
    }

    @ModelAttribute("currentUser")
    Mono<User> currentUser(@AuthenticationPrincipal Mono<User> currentUser){
        return currentUser;
    }

}
