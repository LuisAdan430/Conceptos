package com.IA.Chats.Models;

import lombok.Getter;

@Getter
public class ChatResponse {
    private String respuesta;

    public ChatResponse(String respuesta) {
        this.respuesta = respuesta;
    }

}
