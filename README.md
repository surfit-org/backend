## Git 협업

### Commit

<table>
<tr>
<td width="50%" valign="top">

#### 작업태그(라벨 형식)

| 접두사       | 설명                                |
| ------------ | ----------------------------------- |
| **feat**     | 새로운 기능 추가                      |
| **fix**      | 버그 수정                           |
| **docs**     | 문서 (README 등)                   |
| **refactor** | 코드 리팩토링                        |
| **chore**    | 빌드 설정, 라이브러리 관리 등           |
| **test**    | 테스트 코드 추가 및 수정                |

</td>
<td width="50%" valign="top">

#### 커밋 메세지 형식

```
작업태그: 작업내용
```

**예시**

```
feat: 뉴스 API 연동 기능 추가
fix: 로그인 페이지 버그 수정
```

</td>
</tr>
</table>

---

### Branch

<table>
<tr>
<td width="33%" valign="top">

#### main

```
최종 배포 브랜치
```

- 배포 코드
- develop에서 main으로 merge 시 PR 작성 필수
- PR Approve 1명 이상

</td>
<td width="33%" valign="top">

#### develop

```
개발 메인 브랜치
```

- 브랜치에서 개별 개발 후 develop으로 PR 작성
- PR Approve 1명 이상

</td>
<td width="34%" valign="top">

#### feat/fix

```
feat/admin-page
fix/knowledge-card-bug
```

**예시**

- `feat/main-page`
- `fix/booth-page-ui`

</td>
</tr>
</table>

---

### Issue

- **기능 개발 시작 전 해당 기능 이슈 생성 후 시작** 
- **이슈 생성 시 라벨 붙이기**

<table>
<tr>
<td width="50%" valign="top">

#### 제목 템플릿

```
[개발 파트] 이슈 이름
```

**예시**

```
[FE] 뉴스 피드 무한 스크롤 구현

[BE] 유저 DB 스키마 설계
```

</td>
<td width="50%" valign="top">

#### 본문 템플릿

```markdown
## 기능 설명
- [구현하고자 하는 기능 설명]

## 작업 체크리스트
- 작업 내용 1
- 작업 내용 2

## 참고 사항
- [참고 링크 또는 스크린샷]
```

</td>
</tr>
</table>

---

### Pull Request

- **PR 생성 시 관련 이슈 번호 붙이기**

<table>
<tr>
<td width="50%" valign="top">

#### 제목 템플릿

```
[개발 파트] #이슈번호: 제목
```

**예시**

```
[FE] #1: 로그인 UI 구현

[BE] #12: 회원가입 API 개발

[FE] #5: 뉴스 API 연동 및 테스트
```

</td>
<td width="50%" valign="top">

#### 본문 템플릿

```markdown
## PR 설명

- [PR 설명]
- [PR 설명]

## 작업 상세 내용

- [작업 상세 내용]
- [작업 상세 내용]

## 테스트 여부 및 확인 내용

- [테스트 통과 여부 및 내용]

## 스크린샷 (선택)

## 기타사항 / 참고사항

- [기타사항 / 참고사항]

## 🔗 연관 이슈
- Closes #이슈번호
- 해당 PR이 머지되면 이슈가 자동으로 닫힙니다.
```

</td>
</tr>
</table>