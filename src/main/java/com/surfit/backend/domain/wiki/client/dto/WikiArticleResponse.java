package com.surfit.backend.domain.wiki.client.dto;

import java.util.Map;

public record WikiArticleResponse(Query query) {
	public record Query(Map<String, Page> pages) {
	}

	public record Page(
		int pageid,
		String title,
		String extract
	) {
	}
}
