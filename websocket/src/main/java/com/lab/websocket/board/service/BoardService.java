package com.lab.websocket.board.service;

import com.lab.websocket.board.controller.dto.BoardSaveRequest;
import com.lab.websocket.board.domain.Board;
import com.lab.websocket.board.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;

    @Transactional
    public void createBoard(
            final BoardSaveRequest saveRequest
    ) {
        Board board = Board.of(saveRequest.title(), saveRequest.content());
        boardJpaRepository.save(board);
    }
}
