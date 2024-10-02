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

//여기서 @Setter 는 전체 필드에 적용하지 않음
@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "id"),
        @Index(columnList = "content")
})
@Entity
public class Article {

    @Id    // JPA Persistence Context 가 영속성을 연속화 할 때 자동으로 부여해주는 번호임
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Setter  //특정 필드에만 Set 가능하도록
    public String title;
    @Setter
    public String content;
    @Setter
    public String hashtag;


    /* 메타데이터(데이터에 대한 데이터) :
    메타데이터는 기록의 생성 및 수정에 대한 추적 정보를 저장하고,
    시스템이 데이터의 변경 사항을 관리하는 데 유용한 정보를 제공하는 역할
    - 메타데이터를 private로 설정하는 이유는 객체의 캡슐화(encapsulation) 원칙을 지키기 위해서입니다.
    (캡슐화는 객체 내부의 데이터나 상태를 외부에서 직접 접근하지 못하게 하고, 의도된 방식으로만 접근하도록 제한하는 것)
    */

    //JPA Auditing 어노테이션 사용
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy; //인증기능을 사용하거나 현재 사용하를 모르는 상태임 -> 이름 정보를 넣어주기 위해 config 설정
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @LastModifiedBy
    private String modifiedBy;

}
