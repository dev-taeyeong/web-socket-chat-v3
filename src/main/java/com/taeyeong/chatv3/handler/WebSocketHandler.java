package com.taeyeong.chatv3.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taeyeong.chatv3.core.domain.ChatRoom;
import com.taeyeong.chatv3.core.service.ChatService;
import com.taeyeong.chatv3.handler.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("{}", payload);
        log.info(session.getId());
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom chatRoom = chatService.findRoomById(chatMessage.getRoomId());

        if (chatMessage.getMessage().equals("ccccc")) {
            chatRoom.disconnectSession(session);
        } else {
            if (chatRoom.isConnected(session.getId())) {
                chatRoom.sendMessage(chatMessage.getMessage());
            } else {
                chatRoom.connectSession(session);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session + " 클라이언트 접속 해제");
    }
}
