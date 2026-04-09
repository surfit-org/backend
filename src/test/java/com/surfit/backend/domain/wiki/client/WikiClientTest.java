package com.surfit.backend.domain.wiki.client;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.surfit.backend.domain.wiki.client.dto.WikiArticleResponse;
import com.surfit.backend.domain.wiki.client.dto.WikiImageInfoResponse;

@SpringBootTest
public class WikiClientTest {

	@Autowired
	private WikiClient wikiClient;

	@Test
	void 위키_본문_수집_테스트() {
		String title = "반도체";

		WikiArticleResponse response = wikiClient.fetchWikiArticle(title);

		assertThat(response).isNotNull();
		assertThat(response.query()).isNotNull();
		assertThat(response.query().pages()).isNotEmpty();
	}

	@Test
	void 위키_이미지_수집_테스트() {
		String title = "반도체";

		WikiImageInfoResponse response = wikiClient.fetchImageBatch(title);

		assertThat(response).isNotNull();
		assertThat(response.query()).isNotNull();
	}
}
