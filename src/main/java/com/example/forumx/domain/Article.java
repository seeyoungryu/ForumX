package com.example.forumx.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

//여기서 @Setter 는 전체 필드에 적용하지 않음
@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "id"),
        @Index(columnList = "content")
})
@Entity
public class Article {

    @Id    // JPA Persistence Context 가 영속성을 연속화 할 때 자동으로 부여해주는 번호임
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


    /* 메타데이터(데이터에 대한 데이터) :
    메타데이터는 기록의 생성 및 수정에 대한 추적 정보를 저장하고,
    시스템이 데이터의 변경 사항을 관리하는 데 유용한 정보를 제공하는 역할
    -> 메타데이터를 private로 설정하는 이유는 객체의 캡슐화(encapsulation) 원칙을 지키기 위해서입니다.
    (캡슐화는 객체 내부의 데이터나 상태를 외부에서 직접 접근하지 못하게 하고, 의도된 방식으로만 접근하도록 제한하는 것)
    */

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

    //기본 생성자
    private Article() {
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


    //동등성 비교 (-> 여기서는 not null (id 값 체크 안해서 Objects.equals 의 경우 null 인 경우 포함하게 됨))
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id !== null && == id.equals(article.id);
        // -> 아직 영속화 되지 않은 모든 엔티티는 동등성 검사를 탈락함을 의미
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}



