package com.surfit.backend.domain.interaction.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "view_log",
        uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "post_id"})
)
@Getter
@NoArgsConstructor
public class ViewLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long postId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // new ViewLog(null, memberId, postId, null, null); 이렇게 만들어도 되지만
    // 나머지 값들은 어차피 자동으로 채워지니까 편의를 위해 아래 메소드를 만들었습니다.
    public static ViewLog of(Long memberId, Long postId) {
        ViewLog viewLog = new ViewLog();
        viewLog.memberId = memberId;
        viewLog.postId = postId;
        return viewLog;
    }
}
