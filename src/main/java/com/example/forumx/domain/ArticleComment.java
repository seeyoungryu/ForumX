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
import java.util.Objects;


@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy ")
})
//@EntityListeners(AuditingEntityListener.class)
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


    // <객체 생성 관련>
    //기본 생성자 (롬복 ->  @NoArgsConstructor(access = AccessLevel.PROTECTED)
    protected ArticleComment() {
    }

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    //정적 팩토리 메소드 of : 객체를 생성하기 위한 대체 방법
    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    //동등성 비교 (-> 여기서는 not null (id 값 체크 안해서 Objects.equals 의 경우 null 인 경우 포함하게 됨))
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && id.equals(that.id);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    /* 접근제어자
    public(완전공개 ~ 어디서나 해당 클래스, 메서드, 필드 모든곳에 접근 가능)
    protected(같은 패키지 + 상속받은 클래스에서 접근 가능)
    package-private(같은 패키지 내에서 접근 가능) -> 디폴트 값 접근 제어 (명시x)
    private *가장 제한적* (같은 클래스 내에서만 접근 가능)
     */

}
