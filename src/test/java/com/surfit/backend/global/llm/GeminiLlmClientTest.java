package com.surfit.backend.global.llm;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.surfit.backend.global.llm.gemini.GeminiLlmClient;

@SpringBootTest
public class GeminiLlmClientTest {

	@Autowired
	private GeminiLlmClient geminiLlmClient;

	@Test
	void 제미나이_호출_테스트() {
		String response = geminiLlmClient.complete(
			"You are a helpful assitant. Answer in one sentence.",
			"What is the capital of South Korea?"
		);

		System.out.println("제미나이 응답: " + response);
		assertThat(response).isNotBlank();
	}
}
