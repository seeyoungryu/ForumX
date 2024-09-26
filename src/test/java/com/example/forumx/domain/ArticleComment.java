package com.example.forumx.domain;

import java.time.LocalDateTime;

public class ArticleComment {

    public long articleId;
    public String content;

    //메타데이터
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
