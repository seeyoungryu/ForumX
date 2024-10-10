package com.example.forumx.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

//여기서 @Setter 는 전체 필드에 적용하지 않음
@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy ")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article extends AuditingFields {    //추출한 중복 필드 (여기서는 메타데이터) *상속으로 연결*
    // -> AuditingFields 안에 있는 필드 네 개가 모두 현재 필드의 일부가 됨


    @Id // id -> JPA Persistence Context 가 영속성을 연속화 할 때 자동으로 부여해주는 번호임
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Setter  //특정 필드에만 Set 가능하도록
    @Column(nullable = false) // 컬럼의 nullable 기본값이 true (비어있어도 되면 생략해도됨)
    public String title;
    @Setter
    @Column(nullable = false, length = 10000)
    public String content;
    @Setter
    public String hashtag;


    //(댓글 - 연관관계 매핑을 위한 코드)
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

      /*
    Set은 중복을 허용하지 않는 컬렉션
    ㄴ 여기서는 ArticleComment라는 타입의 객체들을 중복 없이 관리하기 위해 Set을 사용하고 있습니다.
    ㄴ 이 코드를 통해 articleComments라는 변수가 빈 LinkedHashSet으로 초기화됩니다.
    articleComments 변수는 댓글(ArticleComment) 객체들을 저장하는 역할을 하고, 중복된 댓글이 추가되지 않도록 관리해줍니다.
  -------------------------------------------------------------------------------------------------
    -> 정리: 이 코드는 Article 객체와 댓글(ArticleComment) 간의 양방향 관계를 관리하기 위한 필드입니다.
    댓글은 여러 개가 존재할 수 있지만, 중복된 댓글은 허용되지 않으며, 댓글이 추가된 순서를 유지하기 위해 *LinkedHashSet* 을 사용했습니다.
    -------------------------------------------------------------------------------------------------
    - Set<객체타입> : ArticleComment라는 타입의 객체들 (< >: 제네릭, 해당 타입의 객체들만 다룰 것이라고 명확히 지정함)
    - LinkedHashSet : 자바에서 제공하는 Set의 일종(Set의 구체적인 구현체), 데이터가 추가된 <순서>를 유지하면서도 <중복을 허용하지 않는 자료구조>
     */



    /* @ToString.Exclude - 순환참조 설명

    @ToString.Exclude: 순환 참조 문제를 방지하기 위해 articleComments 필드를 toString()에서 제외합니다.
    순환 참조 문제는 두 엔티티가 서로를 참조할 때 무한 루프에 빠질 수 있는 상황을 말합니다.
    예를 들어, Article 객체가 ArticleComment를 참조하고, ArticleComment 객체가 다시 Article을 참조하는 경우,
    toString() 호출 시 무한 루프가 발생하여 프로그램이 멈출 수 있습니다.

    참고 : 댓글은 게시글과 연관된 일대다 관계입니다.
    이 관계에서 toString()을 호출할 때 순환 참조 문제를 방지하기 위해
    댓글(articleComments)을 toString() 메서드에서 제외하여 안전한 객체 사용을 보장한 것입니다.
     */

/* 필드 추출을 위해 주석처리
    //JPA Auditing 어노테이션 사용
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt; //생성일시
    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createdBy; //생성자
    //인증기능을 사용하거나 현재 사용하를 모르는 상태임 -> 이름 정보를 넣어주기 위해 config 설정
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt; //수정일시
    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy; //수정자
 */


       /* 메타데이터(데이터에 대한 데이터) :
    메타데이터는 기록의 생성 및 수정에 대한 추적 정보를 저장하고,
    시스템이 데이터의 변경 사항을 관리하는 데 유용한 정보를 제공하는 역할
    -> 메타데이터를 private로 설정하는 이유는 객체의 캡슐화(encapsulation) 원칙을 지키기 위해서입니다.
    (캡슐화는 객체 내부의 데이터나 상태를 외부에서 직접 접근하지 못하게 하고, 의도된 방식으로만 접근하도록 제한하는 것)
    */


    //기본 생성자
    protected Article() {
    }

    //기본 필드 (메타데이터 제외)
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }


    //*정적 팩토리 메소드*
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    /* -> of :  정적 팩토리 메서드, Article 객체를 생성하는 역할
    정적 팩토리 메서드는 객체를 생성하기 위한 대체 방법입니다. << new 키워드로 직접 객체를 생성하는 대신, >> 정적 메서드를 통해 객체를 생성하는 방식입니다.
    정적 팩토리 메서드는 생성자와 유사한 역할을 하지만, 이름을 자유롭게 지을 수 있고, 필요에 따라 객체 생성 방식을 유연하게 제어할 수 있습니다.(가독성, 객체 생성을 유연하게 제어)
     */


     /* 기본생성자 - protected
    - Hibernate와 같은 프레임워크는 기본 생성자를 통해 객체를 생성하고 필드를 초기화합니다.
    하지만 애플리케이션 개발자는 이러한 내부 로직에 관여할 필요가 없습니다.
    기본 생성자를 protected로 만들어 두면, Hibernate가 내부적으로만 객체를 생성할 수 있고, 애플리케이션 코드에서는 기본 생성자 사용을 제한할 수 있습니다.

    - 테스트 및 유지보수성 향상: 기본 생성자를 protected로 하면,
    테스트 코드나 클래스를 사용하는 다른 코드에서 직접 객체 생성을 제한함으로써 더 견고한 객체 관리가 가능합니다.
    이렇게 객체 생성을 제한함으로써 객체의 일관성을 유지하고 잘못된 객체 생성을 방지할 수 있습니다.

    < protected 기본 생성자의 영향 >
    *외부 클래스에서는 기본 생성자 호출이 불가능
    * 기본 생성자가 protected로 선언되면, 같은 패키지에 속하지 않거나 해당 클래스를 상속받지 않은 클래스에서는 이 생성자를 호출할 수 없습니다.
     즉, 외부에서 직접 객체를 생성하는 것을 방지할 수 있습니다.
    * 프레임워크(Hibernate)에는 영향 없음: Hibernate는 리플렉션을 통해 protected 생성자에 접근할 수 있기 때문에,
    기본 생성자가 protected로 선언되어 있어도 Hibernate는 정상적으로 엔티티 객체를 생성할 수 있습니다.
    * 상속 관계에서 사용 가능: 상속을 사용하는 경우, 서브클래스는 protected 기본 생성자를 호출할 수 있습니다.
    이를 통해 하위 클래스에서 상위 클래스의 생성자를 자유롭게 사용할 수 있습니다.
     */


    // < equals and hashCode >
    //동등성 비교 (-> 여기서는 not null (id 값 체크 안해서 Objects.equals 의 경우 null 인 경우 포함하게 됨))
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
        // -> 아직 영속화 되지 않은 모든 엔티티는 동등성 검사를 탈락함을 의미
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}



