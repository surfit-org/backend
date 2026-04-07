package com.surfit.backend.domain.wiki.client.dto;

import java.util.List;
import java.util.Map;

public record WikiImageInfoResponse(Query query) {
	public record Query(Map<String, Page> pages) {
	}

	public record Page(
		String title,
		List<ImageInfo> imageinfo
	) {
	}

	public record ImageInfo(
		String url,
		ExtMetadata extmetadata
	) {
	}

	public record ExtMetadata(
		ValueWrapper LicenseShortName,
		ValueWrapper UsageTerms,
		ValueWrapper Artist,
		ValueWrapper LicenseUrl
	) {
	}

	public record ValueWrapper(String value) {
	}
}
