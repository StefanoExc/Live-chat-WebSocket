package com.chatting.livechat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); //endpoint dove vogliamo abilitare il simpleBroker o dove aggiungere il prefisso di destinazione dell'applicazione
        registry.enableSimpleBroker("/topic");
    }
}


// 1 classe creata, aggiunta delle annotation e dell'implements
// WebSocketMessageBrokerConfigurer è un'interfaccia e useremo solamente due funzioni che ha  di default: registerStompEndpoints e configureMessageBroker
// creazione dei metodi override