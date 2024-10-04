package com.example.forumx.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy ")
})
@Entity
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Setter
    @ManyToOne(optional = false)
    private Article article; //게시글 (ID) -> *객체 지향적으로 연관관계 매핑하기

    @Setter
    @Column(nullable = false, length = 500)
    private String content; //본문


    //메타데이터
    //JPA Auditing 어노테이션 사용
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt; //생성일시
    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createdBy; //생성자
    //인증기능을 사용하거나 현재 사용하를 모르는 상태임 -> 이름 정보를 넣어주기 위해 config 설정
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt; //수정일시
    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy; //수정자
}
