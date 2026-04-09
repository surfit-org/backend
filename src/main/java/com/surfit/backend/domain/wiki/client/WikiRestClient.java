package com.surfit.backend.domain.wiki.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.surfit.backend.domain.wiki.client.dto.WikiArticleResponse;
import com.surfit.backend.domain.wiki.client.dto.WikiImageInfoResponse;

@Component
public class WikiRestClient implements WikiClient {
	private static final String WIKI_API_BASE_URL = "https://ko.wikipedia.org";

	private final RestClient restClient;

	public WikiRestClient(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder
			.baseUrl(WIKI_API_BASE_URL)
			.defaultHeader("User-Agent", "SurfitWikiBot/1.0")
			.build();
	}

	@Override
	public WikiArticleResponse fetchWikiArticle(String title) {
		return restClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/w/api.php")
				.queryParam("action", "query")
				.queryParam("prop", "extracts")
				.queryParam("format", "json")
				.queryParam("explaintext", 1)
				.queryParam("titles", title)
				.build())
			.retrieve()
			.body(WikiArticleResponse.class);
	}

	@Override
	public WikiImageInfoResponse fetchImageBatch(String title) {
		return restClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/w/api.php")
				.queryParam("action", "query")
				.queryParam("generator", "images")
				.queryParam("prop", "imageinfo")
				.queryParam("format", "json")
				.queryParam("iiprop", "extmetadata|url")
				.queryParam("titles", title)
				.build())
			.retrieve()
			.body(WikiImageInfoResponse.class);
	}
}
