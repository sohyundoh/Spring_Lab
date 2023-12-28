package com.lab.websocket.board.controller;

import com.lab.websocket.board.controller.dto.BoardSaveRequest;
import com.lab.websocket.board.service.BoardService;
import com.lab.websocket.common.dto.SuccessResponse;
import com.lab.websocket.exception.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public SuccessResponse createBoard(@RequestBody final BoardSaveRequest saveRequest) {
        boardService.createBoard(saveRequest);
        return SuccessResponse.of(SuccessMessage.BOARD_CREATE_SUCCESS, "");
    }
}
