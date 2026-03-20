package com.surfit.backend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "테스크 API", description = "스웨거 테스트")
@RestController
public class TestController {

    @Operation(summary = "스웨거 테스트", description = "잘 돌아가네요^^")
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
