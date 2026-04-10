package com.surfit.backend.domain.category.repository;

import com.surfit.backend.domain.category.entity.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {

    /**
     * 유저의 관심 카테고리 목록 조회
     *
     * @param userId 조회할 유저 아이디
     * @return 유저의 관심 카테고리 리스트
     */
    List<UserInterest> findByUserId(Long userId);
}
