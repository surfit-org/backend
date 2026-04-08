package com.surfit.backend.global.llm;

public interface LlmClient {
	String complete(String systemPrompt, String userPrompt);
}
