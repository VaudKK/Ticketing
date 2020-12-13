package com.sda.ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TicketingApplication {

//    @Bean
//    WebClient webClient(ReactiveClientRegistrationRepository reactiveClientRegistrationRepository,
//                        ServerOAuth2AuthorizedClientRepository serverOAuth2AuthorizedClientRepository){
//        ServerOAuth2AuthorizedClientExchangeFilterFunction filterFunction = new ServerOAuth2AuthorizedClientExchangeFilterFunction(reactiveClientRegistrationRepository,
//                serverOAuth2AuthorizedClientRepository);
//        filterFunction.setDefaultClientRegistrationId("keycloak");
//        return WebClient.builder()
//                .filter(filterFunction)
//                .build();
//    }

    public static void main(String[] args) {
        SpringApplication.run(TicketingApplication.class, args);
    }
    
}
