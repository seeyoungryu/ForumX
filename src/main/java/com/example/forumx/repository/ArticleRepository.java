package com.example.forumx.repository;

import com.example.forumx.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;


/* <레파지토리를 인터페이스로 사용하는 이유 관련>
는 인터페이스로 선언해야 Spring Data JPA가 제공하는 기본 CRUD 기능을 사용할 수 있습니다.
JpaRepository를 인터페이스로 구현하여 ArticleRepository 를 선언하고 JpaRepository를 extends 헤야 합니다.
(JPA레파지토리는 인터페이스임, 구현체가 아니므로!)
그럼 Spring Data JPA가 자동으로 필요한 메서드를 생성하고, 기본 CRUD 기능을 사용할 수 있습니다.
기본 CRUD 메서드(save, findById, delete 등)는 자동으로 제공됩니다.
 */

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
