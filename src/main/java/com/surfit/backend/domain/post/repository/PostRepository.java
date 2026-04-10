package com.surfit.backend.domain.post.repository;

import com.surfit.backend.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 커서 기반 무한스크롤용 릴스 탭 조회
     * 유저 관심 카테고리 내에서 조회한 포스트를 제외하고 최신순으로 반환합니다.
     *
     * @param categoryIds 필터링할 카테고리 id 목록
     * @param viewedPostIds 이미 조회한 포스트 목록
     * @param cursor 마지막으로 받은 포스트 id
     * @param size 한 번에 조회할 포스트 개수
     * @return 조건에 맞는 포스트 목록
     */
    @Query("""
        select p from Post p
        where p.category.id in :categoryIds
        and (:cursor is null or p.id < :cursor)
        and (p.id not in :viewedPostIds)
        order by p.createdAt desc
        limit :size
        """)
    List<Post> findUnviewedPostsByCategoryIds(
            @Param("categoryIds") List<Long> categoryIds,
            @Param("viewedPostIds") List<Long> viewedPostIds,
            @Param("cursor") Long cursor,
            @Param("size") int size
    );
}
