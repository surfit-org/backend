# Javadoc 가이드

코드 가독성과 팀원 팔로업을 위해 주요 메서드에 Javadoc을 작성합니다.
IntelliJ에서 메서드에 마우스를 올리면 팝업으로 설명이 표시됩니다.

---

### 작성 대상

| 대상                        | 작성 여부              |
|:--------------------------|:-------------------|
| 인터페이스 `public` 메서드        | ✅ 필수               |
| 서비스 레이어 `public` 메서드      | ✅ 필수               |
| 외부 API 클라이언트 `public` 메서드 | ✅ 필수               |
| `@Override` 구현체 메서드       | ❌ 생략 (인터페이스에서 상속됨) |
| DTO 필드                    | ❌ 생략 (필드명으로 충분)    |
| `private` 메서드             | ❌ 생략               |

---

### 기본 형식

파라미터, 반환값, 예외가 없는 경우 해당 태그는 생략합니다.

```java
/**
 * 한 줄 기능 설명
 *
 * @param 파라미터명 설명
 * @return 반환값 설명
 * @throws 예외 발생 조건
 */
```

---

### 작성 예시

#### 인터페이스

```java
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
```

#### 구현체 — `@Override` 메서드는 생략

```java

@Component
public class GeminiLlmClient implements LlmClient {

	@Override
	public String complete(String systemPrompt, String userPrompt) {
		// 구현 내용
	}
}
```

#### 서비스

```java

@Service
public class KeywordExtractionService {

	/**
	 * 오늘 날짜 뉴스를 수집하고 LLM으로 키워드를 추출해 KeywordPayload 리스트로 반환한다.
	 *
	 * @return 키워드 및 뉴스 메타데이터가 담긴 KeywordPayload 리스트
	 * @throws Exception LLM 호출 실패 또는 JSON 파싱 오류 시
	 */
	public List<KeywordPayload> extractKeywords() throws Exception {
		// 구현 내용
	}
}
```

---