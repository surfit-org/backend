package com.surfit.backend.domain.reels.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surfit.backend.domain.reels.dto.ReelsFeedResponse;
import com.surfit.backend.domain.reels.service.ReelsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Reels", description = "릴스 탭 API")
@RestController
@RequestMapping("/api/v1/reels")
@RequiredArgsConstructor
public class ReelsController {

	private final ReelsService reelsService;

	@Operation(
		summary = "릴스 피드 조회",
		description = "멤버의 관심 카테고리 기반으로 아직 보지 않은 최신 포스트를 커서 기반으로 조회합니다."
	)
	@GetMapping
	public ResponseEntity<ReelsFeedResponse> getFeed(
		@Parameter(description = "멤버 ID (임시, 추후 JWT로 대체)", example = "1")
		@RequestParam Long memberId,

		@Parameter(description = "마지막으로 받은 포스트 ID (최초 요청 시 생략)", example = "20")
		@RequestParam(required = false) Long cursor,

		@Parameter(description = "한 번에 조회할 포스트 수", example = "10")
		@RequestParam(defaultValue = "10") int size
	) {
		return ResponseEntity.ok(reelsService.getFeed(memberId, cursor, size));
	}

	@Operation(
		summary = "포스트 조회 기록",
		description = "멤버가 특정 포스트를 조회했음을 기록합니다. 이미 기록이 있으면 조회 시각만 갱신합니다."
	)
	@PostMapping("/{postId}/view")
	public ResponseEntity<Void> recordView(
		@Parameter(description = "조회한 포스트 ID", example = "1")
		@PathVariable Long postId,

		@Parameter(description = "멤버 ID (임시, 추후 JWT로 대체)", example = "1")
		@RequestParam Long memberId
	) {
		reelsService.recordView(memberId, postId);
		return ResponseEntity.ok().build();
	}
}
