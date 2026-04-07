package com.surfit.backend.domain.wiki.client.dto;

import java.util.List;

public record WikiDataDto(
	String title,
	String content,
	List<SafeImageDto> images
) {
	public record SafeImageDto(
		String url,
		String licenseName,
		String artist
	) {
	}
}
