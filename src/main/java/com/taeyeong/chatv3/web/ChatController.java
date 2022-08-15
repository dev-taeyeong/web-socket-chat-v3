package com.taeyeong.chatv3.web;

import com.taeyeong.chatv3.core.domain.ChatRoom;
import com.taeyeong.chatv3.core.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("chat")
    public String chatRoomList(Model model) {
        List<ChatRoom> chatRooms = chatService.getAllRoom();
        model.addAttribute("chatRooms", chatRooms);
        return "chat-room-list";
    }

    @GetMapping("chat/{roomId}")
    public String chatRoom(Model model) {
        return "chat-room";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
