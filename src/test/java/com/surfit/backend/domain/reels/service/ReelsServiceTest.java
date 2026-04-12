package com.surfit.backend.domain.reels.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.surfit.backend.domain.reels.dto.ReelsFeedResponse;

@SpringBootTest
@Transactional
public class ReelsServiceTest {

	@Autowired
	private ReelsService reelsService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Long testMemberId;
	private Long testPostId;

	@BeforeEach
	void setUp() {
		jdbcTemplate.update(
			"DELETE FROM view_log WHERE member_id IN (SELECT id FROM (SELECT id FROM member WHERE email = ?) AS tmp)",
			"test@test.com"
		);
		jdbcTemplate.update(
			"DELETE FROM member_interest WHERE member_id IN (SELECT id FROM (SELECT id FROM member WHERE email = ?) AS tmp)",
			"test@test.com"
		);
		jdbcTemplate.update("DELETE FROM member WHERE email = ?", "test@test.com");
		jdbcTemplate.update("DELETE FROM post WHERE keyword = ?", "테스트키워드");
		jdbcTemplate.update("DELETE FROM category WHERE slug = ?", "test");

		jdbcTemplate.update(
			"INSERT INTO member (email, nickname, role, created_at, updated_at) VALUES (?, ?, ?, NOW(), NOW())",
			"test@test.com", "테스터", "USER"
		);
		testMemberId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

		jdbcTemplate.update(
			"INSERT INTO category (name, slug, created_at) VALUES (?, ?, NOW())",
			"테스트", "test"
		);
		Long categoryId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

		jdbcTemplate.update(
			"INSERT INTO post (keyword, view_point, category_id, created_at) VALUES (?, ?, ?, NOW())",
			"테스트키워드", 0, categoryId
		);
		testPostId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
	}

	@Test
	void 관심카테고리_없으면_빈_피드_반환() {
		ReelsFeedResponse response = reelsService.getFeed(testMemberId, null, 10);

		assertThat(response.posts()).isEmpty();
		assertThat(response.hasNext()).isFalse();
		assertThat(response.nextCursor()).isNull();
	}

	@Test
	void 조회_기록_없으면_view_log_저장() {
		reelsService.recordView(testMemberId, testPostId);
	}

	@Test
	void 동일한_포스트_재조회_시_updatedAt_갱신() throws InterruptedException {
		reelsService.recordView(testMemberId, testPostId);
		Thread.sleep(100);
		reelsService.recordView(testMemberId, testPostId);
	}
}
