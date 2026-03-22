package com.surfit.backend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "테스크 API", description = "스웨거 테스트")
@RestController
public class TestController {

    @Operation(summary = "스웨거 테스트", description = "잘 돌아가네요^^")
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @Operation(summary = "DTO 및 응답 테스트", description = "데이터를 입력받아 그대로 돌려주는 기능입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/test/echo")
    public String echo(@RequestBody TestRequest request) {
        return "보낸 메세지: " + request.getMessage();
    }

    // 테스트 DTO(원래는 다른 파일에)
    @Getter
    static class TestRequest {
        @Schema(description = "서버로 보낼 메세지 테스트", example = "하이열 ㅋㅋ")
        private String message;
    }
}
