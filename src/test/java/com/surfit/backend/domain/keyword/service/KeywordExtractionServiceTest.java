package com.surfit.backend.domain.keyword.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.surfit.backend.domain.keyword.dto.KeywordPayload;

@SpringBootTest
public class KeywordExtractionServiceTest {

	@Autowired
	private KeywordExtractionService keywordExtractionService;

	@Test
	void 키워드_추출_테스트() throws Exception {
		List<KeywordPayload> result = keywordExtractionService.extractKeywords();

		System.out.println("추출된 키워드 수: " + result.size());
		result.forEach(payload -> {
			System.out.println("=====");
			System.out.println("제목: " + payload.newsTitle());
			System.out.println("primaryKeyword: " + payload.primaryKeyword());
			System.out.println("fallbackKeyword: " + payload.fallbackKeyword());
			System.out.println("category: " + payload.category());
			System.out.println("curationReason: " + payload.curationReason());
		});

		assertThat(result).isNotEmpty();
		assertThat(result.get(0).primaryKeyword()).isNotBlank();
	}
}
