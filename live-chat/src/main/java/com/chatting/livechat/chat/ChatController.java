package com.chatting.livechat.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    //quando si parla di websocket si parla sempre di payload, quindi dobbiamo aggiungere l'annotation all'oggett ChatMessage
    @MessageMapping("chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

        //Aggiunge lo username alla websocket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        System.out.println(chatMessage);
        return chatMessage;
    }

    @GetMapping("/test")
    public void test () {
        System.out.println("funziono");
    }
}

// 3 classe creata, aggiunta annotation, creazione 2 metodi:
// il primo per aggiungere l'utente, quando si connette alla chat , e bisogna informare tutti gli altri utenti che un nuovo utente è entrato in chat
// e il secondo per mandare un messaggio
//aggiunta del MessageMapping annotation che dice qual è l'URL che voglio usare per invocare questo metodo
// aggiunta del SendTo che definisce dove mandare il topic(argomento)-- il topic viene dal file WebSocketConfig, nel configureMessageBroker (riga 21)
// implemento un secondo metodo  addUser di tipo ChatMessage che prende in entrata anch'esso un @Payload di tipo ChatMessage e un SimpMessageHeaderAccessor