package com.taeyeong.chatv3.core.service;

import com.taeyeong.chatv3.core.domain.ChatRoom;
import com.taeyeong.chatv3.core.dto.CreateChatRoomRequest;
import com.taeyeong.chatv3.core.dto.CreateChatRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final Map<String, ChatRoom> chatRooms;

    public CreateChatRoomResponse createRoom(CreateChatRoomRequest dto) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(dto.getName())
                .build();

        chatRooms.put(chatRoom.getRoomId(), chatRoom);

        return new CreateChatRoomResponse(
                chatRoom.getRoomId(),
                chatRoom.getName()
        );
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public List<ChatRoom> getAllRoom() {
        return chatRooms.values().stream().toList();
    }
}
