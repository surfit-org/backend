package com.surfit.backend.domain.news.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.surfit.backend.domain.news.dto.ArirangNewsItem;
import com.surfit.backend.domain.news.dto.ArirangNewsResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ArirangNewsClient {

	private final RestClient restClient;
	private final String apiKey;
	private static final int PAGE_SIZE = 100;
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public ArirangNewsClient(
		RestClient.Builder restClientBuilder,
		@Value("${arirang.api.base-url}") String baseUrl,
		@Value("${arirang.api.api-key}") String apiKey) {
		this.restClient = restClientBuilder.baseUrl(baseUrl).build();
		this.apiKey = apiKey;
	}

	public List<ArirangNewsItem> fetchTodayNews() {
		List<ArirangNewsItem> result = new ArrayList<>();
		String today = LocalDate.now().format(DATE_FORMATTER);
		int currentPage = 0;

		while (true) {
			int page = currentPage;
			ArirangNewsResponse response = restClient.get()
				.uri(uriBuilder -> uriBuilder
					.queryParam("serviceKey", apiKey)
					.queryParam("pageNo", page)
					.queryParam("numOfRows", PAGE_SIZE)
					.build())
				.retrieve()
				.body(ArirangNewsResponse.class);

			if (response == null || response.items() == null || response.items().isEmpty()) {
				break;
			}

			boolean hasOlderNews = false;
			for (ArirangNewsItem item : response.items()) {
				if (item.broadcastDate().startsWith(today)) {
					result.add(item);
				} else {
					hasOlderNews = true;
				}
			}

			if (hasOlderNews) {
				break;
			}

			currentPage++;
		}

		log.info("{} 기사 {}건 수집 완료", today, result.size());
		return result;
	}
}
