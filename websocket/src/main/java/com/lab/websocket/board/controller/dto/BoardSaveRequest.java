package com.lab.websocket.board.controller.dto;

public record BoardSaveRequest(
        String title,
        String content
) {
}
