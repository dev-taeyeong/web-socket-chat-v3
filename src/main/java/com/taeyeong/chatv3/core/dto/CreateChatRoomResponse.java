package com.taeyeong.chatv3.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRoomResponse {

    private String roomId;
    private String name;
}
