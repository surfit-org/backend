package com.surfit.backend.domain.news.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ArirangNewsItem(
	String title,
	String content,
	@JsonProperty("news_url") String newsUrl,
	@JsonProperty("thum_url") String thumUrl,
	@JsonProperty("broadcast_date") String broadcastDate
) {

}
