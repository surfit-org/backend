package com.surfit.backend.domain.reels.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.surfit.backend.domain.post.entity.Card;
import com.surfit.backend.domain.post.entity.Post;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "릴스 포스트 1개 응답")
public record ReelsItemResponse(

        @Schema(description = "포스트 ID", example = "1")
        Long postId,

        @Schema(description = "키워드", example = "인공지능")
        String keyword,

        @Schema(description = "카테고리 이름", example = "IT")
        String categoryName,

        @Schema(description = "슬라이드 목록")
        List<CardDto> cards,

        @Schema(description = "생성일시")
        LocalDateTime createdAt
) {
    public static ReelsItemResponse from(Post post, List<Card> cards) {
        return new ReelsItemResponse(
                post.getId(),
                post.getKeyword(),
                post.getCategory().getName(),
                cards.stream().map(CardDto::from).toList(),
                post.getCreatedAt()
        );
    }
}
