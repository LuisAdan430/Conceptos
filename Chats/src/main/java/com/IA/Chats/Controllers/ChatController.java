package com.IA.Chats.Controllers;

import com.IA.Chats.Models.ChatRequest;
import com.IA.Chats.Models.ChatResponse;
import com.IA.Chats.Services.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OpenAiService openAiService;

    public ChatController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/DeepSeek")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request){
        String respuesta = openAiService.preguntarADeepseek(request.getPregunta());
        return ResponseEntity.ok(new ChatResponse(respuesta));
    }

}
