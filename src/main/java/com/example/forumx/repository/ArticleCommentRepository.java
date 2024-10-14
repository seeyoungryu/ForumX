package com.example.forumx.repository;

import com.example.forumx.domain.Article;
import com.example.forumx.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository
        extends JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<Article> {


    default void customize(QuerydslBindings querydslBindings, ArticleComment articleComment) {
        querydslBindings.excludeUnlistedProperties(true);
    }

    // QueryDSL에 특화된 커스터마이징 메서드, 쿼리를 사용자 정의 할 때만 사용함


}

