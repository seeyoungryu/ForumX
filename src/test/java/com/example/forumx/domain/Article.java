package com.example.forumx.domain;

import java.time.LocalDateTime;

public class Article {

    public long id;
    public String title;
    public String content;
    public String hashtag;


    /* 메타데이터(데이터에 대한 데이터) :
    메타데이터는 기록의 생성 및 수정에 대한 추적 정보를 저장하고,
    시스템이 데이터의 변경 사항을 관리하는 데 유용한 정보를 제공하는 역할

    - 메타데이터를 private로 설정하는 이유는 객체의 캡슐화(encapsulation) 원칙을 지키기 위해서입니다.
    (캡슐화는 객체 내부의 데이터나 상태를 외부에서 직접 접근하지 못하게 하고, 의도된 방식으로만 접근하도록 제한하는 것)
    */

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

}
