package com.surfit.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surfit.backend.domain.wiki.client.WikiClient;
import com.surfit.backend.domain.wiki.client.dto.WikiArticleResponse;
import com.surfit.backend.domain.wiki.client.dto.WikiImageInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;

@Tag(name = "테스크 API", description = "스웨거 테스트")
@RestController
public class TestController {

	private final WikiClient wikiClient;

	public TestController(WikiClient wikiClient) {
		this.wikiClient = wikiClient;
	}

	@Operation(summary = "위키 본문 수집 테스트", description = "입력한 제목의 위키 본문을 가져옵니다.")
	@GetMapping("/test/wiki/article")
	public WikiArticleResponse testArticle(@RequestParam String title) {
		return wikiClient.fetchWikiArticle(title);
	}

	@Operation(summary = "위키 이미지 정보 테스트", description = "입력한 제목의 문서에 포함된 이미지 상세 정보를 가져옵니다.")
	@GetMapping("/test/wiki/images")
	public WikiImageInfoResponse testImages(@RequestParam String title) {
		return wikiClient.fetchImageBatch(title);
	}

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
