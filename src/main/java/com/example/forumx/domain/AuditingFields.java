package com.example.forumx.domain;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass //JPA 표준 애너테이션임.
public class AuditingFields {


    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt; //생성일시
    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createdBy;        //생성자
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;//수정일시
    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy;       //수정자

}
