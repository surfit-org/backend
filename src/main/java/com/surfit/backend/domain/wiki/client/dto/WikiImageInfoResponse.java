package com.surfit.backend.domain.wiki.client.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

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
		@JsonProperty("LicenseShortName") ValueWrapper licenseShortName,
		@JsonProperty("UsageTerms") ValueWrapper usageTerms,
		@JsonProperty("Artist") ValueWrapper artist,
		@JsonProperty("LicenseUrl") ValueWrapper licenseUrl
	) {
	}

	public record ValueWrapper(String value) {
	}
}
