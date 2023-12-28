package com.lab.websocket;

import com.lab.websocket.board.controller.dto.BoardSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WebsocketApplicationTests {

    @Test
    @DisplayName("정상 요청이 들어왔을 때 ")
    public void 정상_요청이_들어왔을_떄() {
        //given
        BoardSaveRequest saveRequest = new BoardSaveRequest("제목", "내용");

    }

}
