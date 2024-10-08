package com.example.forumx.repository;

import com.example.forumx.config.JpaConfig;
import com.example.forumx.domain.Article;
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



//  < @BeforeEach 사용하여 데이터 초기화 하는 경우 >
//    <테스트 전 데이터 초기화 시>
//    @BeforeEach               : 각 테스트 메서드가 실행되기 전 호출됨
//    void setUp() {            : setUp(): 테스트 전에 "Sample Title"을 가진 Article 객체를 저장
//        articleRepository.save(Article.of("Sample Title", "Sample Content", "#sample"));
//    }                         : Article.of(): 정적 팩토리 메서드를 사용해 Article 객체를 생성하고 저장
//                                -> Article 클래스에서 정의된 of() 메서드를 통해 객체를 생성


    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {          //given_when_then 테스트 패턴
        articleRepository.findAll(); // 쿼리를 확인하기 위한 예시
    }

    @DisplayName("insert test")
    @Test
    void givenNewArticle_whenInserting_thenWorksFine() {
        articleRepository.save(Article.of("New Title", "New Content", "New Hashtag"));
    }

    @DisplayName("update test")
    @Test
    void givenExistingArticle_whenUpdating_thenWorksFine() {
        Article article = articleRepository.findById(1L).get(); // 간단하게 조회
        article.setHashtag("#UpdatedHashtag");
        articleRepository.save(article);
    }

    @DisplayName("delete test")
    @Test
    void givenExistingArticle_whenDeleting_thenWorksFine() {
        articleRepository.deleteById(1L);
    }
}
