package com.surfit.backend.domain.reels.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "릴스 피드 응답")
public record ReelsFeedResponse(

        @Schema(description = "포스트 목록")
        List<ReelsItemResponse> posts,

        @Schema(description = "다음 페이지 존재 여부", example = "true")
        boolean hasNext,

        @Schema(description = "다음 요청 시 사용할 커서 (마지막 포스트 ID)", example = "15")
        Long nextCursor
) {
}
