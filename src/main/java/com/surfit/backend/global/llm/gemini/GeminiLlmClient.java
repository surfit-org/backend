package com.surfit.backend.global.llm.gemini;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.surfit.backend.global.llm.LlmClient;
import com.surfit.backend.global.llm.gemini.dto.GeminiRequest;
import com.surfit.backend.global.llm.gemini.dto.GeminiResponse;

@Component
public class GeminiLlmClient implements LlmClient {

	private final RestClient restClient;

	public GeminiLlmClient(
		RestClient.Builder restClientBuilder,
		@Value("${gemini.api.base-url}") String baseUrl,
		@Value("${gemini.api.model}") String model,
		@Value("${gemini.api.api-key}") String apiKey) {
		this.restClient = restClientBuilder
			.baseUrl(baseUrl + "/" + model + ":generateContent?key=" + apiKey)
			.build();
	}

	@Override
	public String complete(String systemPrompt, String userPrompt) {
		GeminiRequest request = new GeminiRequest(
			new GeminiRequest.Content(List.of(new GeminiRequest.Part(systemPrompt))),
			List.of(new GeminiRequest.Content(List.of(new GeminiRequest.Part(userPrompt))))
		);

		GeminiResponse response = restClient.post()
			.body(request)
			.retrieve()
			.body(GeminiResponse.class);

		return response.candidates().get(0).content().parts().get(0).text();
	}
}
