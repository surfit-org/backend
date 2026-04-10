package com.surfit.backend.domain.keyword.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surfit.backend.domain.keyword.dto.KeywordPayload;
import com.surfit.backend.domain.news.client.ArirangNewsClient;
import com.surfit.backend.domain.news.dto.ArirangNewsItem;
import com.surfit.backend.global.llm.LlmClient;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class KeywordExtractionService {

	private static final String SYSTEM_PROMPT = """
		당신은 지식 큐레이션 서비스 'Surf'의 핵심 분석 엔진입니다.
		제공된 뉴스 목록에서 지식 카드화하기 적합한 기사를 골라 분석 결과를 JSON 배열로 반환하세요.
		
		[분석 지침]
		1. primaryKeyword: 기사의 핵심 주제 중 위키피디아에서 학술적/정보적 정의를 찾을 수 있는 구체적인 영문 명사.
		2. fallbackKeyword: primaryKeyword 검색 실패 시 사용할 수 있는 상위 개념의 일반 영문 명사.
		3. category: [Economy, Tech, Science, Politics, Society] 중 하나로 분류.
		4. curationReason: 이 뉴스가 왜 지식적으로 가치 있는지 한국어 1문장으로 설명.
		
		[출력 형식]
		반드시 아래 JSON 구조만 반환하세요. (마크다운 코드블록 없이 순수 JSON만)
		[
		  {
			"newsUrl": "입력받은 기사의 newsUrl 그대로",
			"primaryKeyword": "...",
			"fallbackKeyword": "...",
			"category": "...",
			"curationReason": "..."
		  }
		]
		""";
	private final ArirangNewsClient arirangNewsClient;
	private final LlmClient llmClient;
	private final ObjectMapper objectMapper;

	public KeywordExtractionService(
		ArirangNewsClient arirangNewsClient,
		LlmClient llmClient,
		ObjectMapper objectMapper) {
		this.arirangNewsClient = arirangNewsClient;
		this.llmClient = llmClient;
		this.objectMapper = objectMapper;
	}

	/**
	 * 오늘 날짜 뉴스를 수집하고 LLM으로 키워드 추출 및 카테고리 선별을 하여 KeywordPayload 리스트로 반환
	 * LLM이 지식카드화 적합한 기사를 선별하고 primaryKeyword, fallbackKeyword, category, curationReason을 추출
	 *
	 * @return 키워드 및 뉴스 메타데이터가 담긴 KeywordPayload 리스트
	 * @throws Exception LLM 호출 실패 또는 JSON 파싱 오류 시
	 */
	public List<KeywordPayload> extractKeywords() throws Exception {
		List<ArirangNewsItem> newsList = arirangNewsClient.fetchTodayNews();

		String newsJson = objectMapper.writeValueAsString(newsList);
		String userPrompt = "아래 뉴스 목록을 분석해주세요:\n" + newsJson;

		String llmResponse = llmClient.complete(SYSTEM_PROMPT, userPrompt);

		List<LlmResult> llmResults = objectMapper.readValue(llmResponse, new TypeReference<List<LlmResult>>() {
		});

		return llmResults.stream()
			.map(result -> {
				ArirangNewsItem news = newsList.stream()
					.filter(n -> n.newsUrl().equals(result.newsUrl()))
					.findFirst()
					.orElseThrow();
				return new KeywordPayload(
					result.primaryKeyword(),
					result.fallbackKeyword(),
					result.category(),
					news.title(),
					news.content(),
					news.newsUrl(),
					result.curationReason(),
					news.broadcastDate()
				);
			})
			.toList();
	}

	// 내부 record, 위 메소드에서만 사용
	private record LlmResult(
		String newsUrl,
		String primaryKeyword,
		String fallbackKeyword,
		String category,
		String curationReason
	) {
	}
}
