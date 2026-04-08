package com.surfit.backend.domain.news.client;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.surfit.backend.domain.news.dto.ArirangNewsItem;

@SpringBootTest
public class ArirangNewsClientTest {

	@Autowired
	private ArirangNewsClient arirangNewsClient;

	@Test
	void 오늘_뉴스_수() {
		List<ArirangNewsItem> items = arirangNewsClient.fetchTodayNews();

		assertThat(items).isNotNull();
		System.out.println("수집된 기사 수: " + items.size());
		items.forEach(item -> System.out.println(item.title()));
	}
}
