package com.chatting.livechat.config;

import com.chatting.livechat.chat.ChatMessage;
import com.chatting.livechat.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        //Creo un oggetto di tipo StompHeaderAccessor
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        //creo una Stringa username. Faccio un cast perchè è un oggetto
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        //una volta che ho ottenuto l'username faccio un controllo che l'username non sia null
        if(username != null) {
            log.info("L'utente {} si è disconnesso", username);
            ChatMessage chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            // questo informerà tutti gli utenti dell'uscita di un utente dalla chat puntando al path dove tutti gli utenti saranno presenti
            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }

}

// 2 classe creata, aggiunta annotation e creazione handleWebSocketDisconnectListener e aggiunta dell'annotation eventListener
//implementazione del metodo per la disconnessione
//bisogna avvisare gli altri utenti che un utente è uscito dalla chat quindi definisco  una costante SimpMessageSendingOperations