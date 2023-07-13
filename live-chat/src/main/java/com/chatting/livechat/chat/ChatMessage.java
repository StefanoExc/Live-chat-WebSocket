package com.chatting.livechat.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private String content;

    private String sender;

    private MessageType type;
}

// 4 classe creata, aggiunta variabili (content, sender, type)
//aggiunta di annotation getter e setter, all e no argsconstructor
