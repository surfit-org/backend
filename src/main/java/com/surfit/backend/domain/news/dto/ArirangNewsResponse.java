package com.surfit.backend.domain.news.dto;

import java.util.List;

public record ArirangNewsResponse(
	int resultCode,
	String resultMsg,
	int numOfRows,
	int pageNo,
	int totalCount,
	List<ArirangNewsItem> items
) {
}
