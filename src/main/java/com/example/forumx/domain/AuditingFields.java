package com.example.forumx.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class) //이벤트 리스너
@MappedSuperclass
public class AuditingFields {


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; //생성일시

    @CreatedBy
    @Column(nullable = false, length = 100, updatable = false)
    private String createdBy;        //생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;//수정일시

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy;       //수정자

}




/* 정리


1. @MappedSuperclass : JPA 애너테이션, 공통속성 클래스임.
이 클래스를 상속하는 엔티티의 테이블에 <필드>가 추가됨

2. @EntityListner : JPA는 엔티티의 생성 및 수정시 이벤트를 감지하여 Auditing을 처리함,
AuditingEntityListener는 엔티티가 생성되거나 수정될 때 자동으로 메타데이터를 채우는 역할을 함

3. @CreatedBy 의 경우 (@LastModifiedBy 의 경우도 동일)
,스프링 시큐리티와 같은 인증시스템과 연동해 현재 로그인한 사용자 정보를 자동으로 기록할 수 있음

4. 인덱스 설정 관련 :
이 클래스는 인덱스 설정이 없음, AuditingFields 클래스가 공통 메타데이터 필드를 제공하는 역할을 하며
,직접적으로 테이블과 매핑되지 않기 때문임! ( *인덱스 설정 -> 본 클래스를 상속받는 <실제 엔티티>에서 지정해야 함.

5. 영속성 컨텍스트 관련 :
JPA가 엔티티를 관리하는 <영역>인 *영속성 컨텍스트*는
,애플리케이션이 데이터베이스와 상호작용 할 때 그 데이터를 메모리에 저장해두고 변경사항을 관리함.
본 클래스의 필드도 영속성 컨텍스트에서 관리되며, 엔티티가 영속성 컨텍스트에 저장될때 <자동으로 값이 설정됨.


 */




