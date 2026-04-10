package com.surfit.backend.global.llm;

public interface LlmClient {

	/**
	 * LLM에 시스템 프롬프트와 사용자 프롬프트를 전달하고 생성된 텍스트를 반환한다.
	 *
	 * @param systemPrompt LLM의 역할 및 출력 형식을 지시하는 고정 프롬프트
	 * @param userPrompt   실제 분석할 데이터 또는 질문
	 * @return LLM이 생성한 텍스트 응답
	 */
	String complete(String systemPrompt, String userPrompt);
}
