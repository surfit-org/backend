package com.surfit.backend.domain.reels.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfit.backend.domain.category.repository.MemberInterestRepository;
import com.surfit.backend.domain.interaction.entity.ViewLog;
import com.surfit.backend.domain.interaction.repository.ViewLogRepository;
import com.surfit.backend.domain.post.entity.Card;
import com.surfit.backend.domain.post.entity.Post;
import com.surfit.backend.domain.post.repository.CardRepository;
import com.surfit.backend.domain.post.repository.PostRepository;
import com.surfit.backend.domain.reels.dto.ReelsFeedResponse;
import com.surfit.backend.domain.reels.dto.ReelsItemResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReelsService {

	private final PostRepository postRepository;
	private final CardRepository cardRepository;
	private final MemberInterestRepository memberInterestRepository;
	private final ViewLogRepository viewLogRepository;

	/**
	 * 커서 기반 무한스크롤 릴스 피드를 조회합니다.
	 * 멤버의 관심 카테고리 내에서 아직 조회하지 않은 최신 포스트를 반환합니다.
	 *
	 * @param memberId 조회 요청한 멤버 ID
	 * @param cursor 마지막으로 받은 포스트 ID (최초 요청 시 null)
	 * @param size 한 번에 조회할 포스트 수
	 * @return 포스트 목록, 다음 페이지 여부, 다음 커서
	 */
	@Transactional(readOnly = true)
	public ReelsFeedResponse getFeed(Long memberId, Long cursor, int size) {
		List<Long> categoryIds = memberInterestRepository.findByMemberId(memberId)
			.stream()
			.map(interest -> interest.getCategory().getId())
			.toList();

		if (categoryIds.isEmpty()) {
			return new ReelsFeedResponse(List.of(), false, null);
		}

		List<Long> viewedPostIds = viewLogRepository.findByMemberId(memberId).stream().map(ViewLog::getPostId).toList();

		List<Post> posts = postRepository.findUnviewedPostsByCategoryIds(categoryIds,
			viewedPostIds.isEmpty() ? List.of(-1L) : viewedPostIds, cursor, size + 1);

		boolean hasNext = posts.size() > size;
		List<Post> result = hasNext ? posts.subList(0, size) : posts;

		List<ReelsItemResponse> items = result.stream().map(post -> {
			List<Card> cards = cardRepository.findByPostIdOrderBySortOrderAsc(post.getId());
			return ReelsItemResponse.from(post, cards);
		}).toList();

		Long nextCursor = hasNext ? result.get(result.size() - 1).getId() : null;

		return new ReelsFeedResponse(items, hasNext, nextCursor);
	}

	/**
	 * 포스트 조회 시 view_log를 기록하거나 갱신합니다.
	 * 이미 조회 이력이 있으면 updatedAt만 갱신합니다.
	 *
	 * @param memberId 조회한 멤버 ID
	 * @param postId 조회한 포스트 ID
	 */
	@Transactional
	public void recordView(Long memberId, Long postId) {
		viewLogRepository.findByMemberIdAndPostId(memberId, postId)
			.ifPresentOrElse(
				viewLog -> {
					viewLog.touch();
					viewLogRepository.save(viewLog);
				},
				() -> viewLogRepository.save(ViewLog.of(memberId, postId))
			);
	}

}
