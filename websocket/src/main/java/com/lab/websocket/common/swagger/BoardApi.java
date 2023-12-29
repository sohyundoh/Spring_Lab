package com.lab.websocket.common.swagger;

import com.lab.websocket.board.controller.dto.BoardSaveRequest;
import com.lab.websocket.common.dto.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "게시글 관련 API")
public interface BoardApi {

    @Operation(summary = "게시글 등록 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "게시글이 정상적으로 생성되었습니다."),
                    @ApiResponse(responseCode = "400", description = "제목이 입력되지 않았습니다.\n" +
                    "내용이 입력되지 않았습니다.\n"),
                    @ApiResponse(responseCode = "500",description = "서버 내부 오류입니다.")
            }
    )
    public SuccessResponse createBoard(@RequestBody @Valid final BoardSaveRequest saveRequest);
}
