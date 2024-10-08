package com.example.forumx.repository;

import com.example.forumx.config.JpaConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)  //Audting config 관련 (가져와야하므로)
@DataJpaTest //(JPA 빈만 로드하도록 함)
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;


    // 명시적 생성자 주입
    JpaRepositoryTest(ArticleRepository articleRepository,
                      ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }


    // @Autowired 어노테이션을 통한 스프링 ~ 자동 생성자 주입 방식
    //    public JpaRepositoryTest(
    //            @Autowired ArticleRepository articleRepository,
    //            @Autowired ArticleCommentRepository articleCommentRepository
    //    ) {
    //        this.articleRepository = articleRepository;
    //        this.articleCommentRepository = articleCommentRepository;
    //    }

         /*
            생성자를 통한 의존성 주입(현재 코드: 명시적 생성자 주입/주석: 스프링이 자동으로 주입)
            생성자에 ArticleRepository와 ArticleCommentRepository를 인자로 받아 클래스 필드에 할당.
         */


    @DisplayName("select test")
    @Test
        //given_when_then 테스트 패턴
    void givenTestData_whenSelecting_thenWorksFine() {

    }

}