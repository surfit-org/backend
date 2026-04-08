package com.surfit.backend.global.llm.gemini.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeminiRequest(
	@JsonProperty("system_instruction") Content systemInstruction,
	List<Content> contents
) {
	public record Content(List<Part> parts) {
	}

	public record Part(String text) {
	}
}
