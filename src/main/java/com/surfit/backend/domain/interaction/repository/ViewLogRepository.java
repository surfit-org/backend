package com.surfit.backend.domain.interaction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surfit.backend.domain.interaction.entity.ViewLog;

public interface ViewLogRepository extends JpaRepository<ViewLog, Long> {

	/**
	 * 멤버가 조회한 포스트 id를 조회합니다.
	 * 릴스 탭에서 이미 본 포스트를 조회할 때 사용합니다.
	 *
	 * @param memberId 조회할 멤버 id
	 * @return 해당 멤버가 조회한 포스트 id 목록
	 */
	List<ViewLog> findByMemberId(Long memberId);

	/**
	 * 멤의 특정 포스트 조회 이력 조회합니다.
	 * 중복 저장 방지 및 재조회 시 updatedAt 갱신 여부 판단에 사용합니다.
	 *
	 * @param memberId 조회할 멤버 id
	 * @param postId 조회할 포스트 id
	 * @return 조회 이력, 없으면 empty
	 */
	Optional<ViewLog> findByMemberIdAndPostId(Long memberId, Long postId);
}
