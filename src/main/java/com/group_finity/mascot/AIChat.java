package com.group_finity.mascot;

import java.net.http.*;
import java.net.URI;

public class AIChat {
    private static final String API_KEY = "AIzaSy..."; // Lát thay key thật
    
    public static String ask(String question) {
        try {
            String json = "{\"contents\":[{\"parts\":[{\"text\":\"" + question + "\"}]}]}";
            HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY))
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(json))
              .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().split("\"text\":\"")[1].split("\"")[0].replace("\\n", "\n");
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
    }
}
