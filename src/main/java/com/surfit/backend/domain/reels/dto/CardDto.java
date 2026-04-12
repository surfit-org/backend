package com.surfit.backend.domain.reels.dto;

import com.surfit.backend.domain.post.entity.Card;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카드뉴스 슬라이드 1장")
public record CardDto(

	@Schema(description = "슬라이드 순서", example = "1")
	int sortOrder,

	@Schema(description = "슬라이드 이미지 URL", example = "https://example.com/image.jpg")
	String imageUrl,

	@Schema(description = "슬라이드 텍스트 내용", example = "오늘의 주요 뉴스입니다.")
	String content
) {
	public static CardDto from(Card card) {
		return new CardDto(card.getSortOrder(), card.getImageUrl(), card.getContent());
	}
}
