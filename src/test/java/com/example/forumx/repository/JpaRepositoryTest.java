package com.example.forumx.repository;

import com.example.forumx.config.JpaConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleCommentRepository articleCommentRepository;

    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {          //given_when_then 테스트 패턴
        articleRepository.findAll(); // 쿼리를 확인하기 위한 예시
    }
}
