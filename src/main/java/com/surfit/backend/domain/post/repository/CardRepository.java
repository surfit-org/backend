package com.surfit.backend.domain.post.repository;

import com.surfit.backend.domain.post.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    /**
     * 포스트에 속한 카드를 순서대로 전체 조회합니다.
     * @param postId 조회할 포스트 id
     * @return 해당 포스트에 속한 카드 목록
     */
    List<Card> findByPostIdOrderBySortOrderAsc(Long postId);
}
