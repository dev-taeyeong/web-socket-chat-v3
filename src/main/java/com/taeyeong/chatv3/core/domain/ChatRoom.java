package com.taeyeong.chatv3.core.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {

    private String roomId;
    private String name;
    private final Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void connectSession(WebSocketSession session) {
        this.sessions.add(session);
        sendMessage("~님이 입장했습니다.");
    }

    public void disconnectSession(WebSocketSession session) {
        this.sessions.remove(session);
        sendMessage("~님이 퇴장했습니다.");
    }

    public Boolean isConnected(String sessionId) {
        return sessions.stream()
                .anyMatch(session -> session.getId().equals(sessionId));
    }

    public void sendMessage(String message) {
        sessions.parallelStream()
                .forEach(session -> {
                    try {
                        session.sendMessage(new TextMessage(message));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
    }
}
