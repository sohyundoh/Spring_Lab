package com.lab.websocket.board;

import com.lab.websocket.board.controller.dto.BoardSaveRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void beforeEach() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("정상 요청이 들어왔을 떄")
    void 정상_요청이_들어왔을_때() {
        var saveRequest = new BoardSaveRequest("제목", "내용");
        //given
        var request = RestAssured.given()
                .body(saveRequest)
                .contentType(ContentType.JSON);
        //when
        var response  = request
                .post("/api/v1/board");
        //then
        response.then().statusCode(200);

    }
}
