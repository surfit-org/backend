package com.surfit.backend.domain.keyword.dto;

public record KeywordPayload(
	String primaryKeyword,
	String fallbackKeyword,
	String category,
	String newsTitle,
	String newsContent,
	String newsUrl,
	String broadcastDate
) {
}
