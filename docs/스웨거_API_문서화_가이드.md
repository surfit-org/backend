# Swagger API 가이드

우리 프로젝트의 **API 명세 자동화** 및 **테스트**를 위한 가이드입니다.
코드를 작성하면 실시간으로 문서가 업데이트되며, 웹에서 바로 API를 호출해 볼 수 있습니다.

- **접속 주소**: `http://localhost:8080/swagger-ui/index.html`
- **실행 조건**: 로컬 서버가 실행 중이어야 접속 가능합니다.

---

### 핵심 어노테이션 (이 4개만 사용하는 연습을 해봅시다.)

| 어노테이션 | 위치 | 역할 |
| :--- | :--- | :--- |
| **@Tag** | 클래스 상단 | 도메인별 API 그룹핑 (News, Member 등) |
| **@Operation** | 메서드 상단 | API 기능의 한글 명칭 및 상세 설명 |
| **@Schema** | DTO 필드 | 데이터 설명 및 **테스트용 예시값(example)** 설정 |
| **@ApiResponse** | 메서드 상단 | 성공(200) 및 실패(400, 500 등) 상황별 응답 설명 |

#### 사용 예시
```java
@Tag(name = "News", description = "뉴스 요약 및 관리 API")
@RestController
public class NewsController {

    @Operation(summary = "뉴스 카드 생성", description = "URL을 입력받아 AI 요약 카드를 만듭니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "카드 생성 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 URL 형식"),
        @ApiResponse(responseCode = "500", description = "LLM 서버 통신 오류")
    })
    @PostMapping("/cards")
    public ResponseEntity<NewsResponse> create(@RequestBody NewsRequest request) { ... }
}

public class NewsRequest {
    @Schema(description = "기사 원문 URL", example = "[https://surfit.io/123](https://surfit.io/123)")
    private String url;
}