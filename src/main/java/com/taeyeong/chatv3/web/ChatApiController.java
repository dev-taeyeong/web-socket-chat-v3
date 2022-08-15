package com.taeyeong.chatv3.web;

import com.taeyeong.chatv3.core.dto.CreateChatRoomRequest;
import com.taeyeong.chatv3.core.dto.CreateChatRoomResponse;
import com.taeyeong.chatv3.core.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatApiController {

    private final ChatService chatService;

    @PostMapping
    public CreateChatRoomResponse createChatRoom(
            @RequestBody CreateChatRoomRequest request
    ) {
        return chatService.createRoom(request);
    }
}
