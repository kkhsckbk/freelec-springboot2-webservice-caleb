package org.example.caleb.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter // lombok 어노테이션: Getter 메소드 자동 생성
@NoArgsConstructor // lombok 어노테이션: 기본 생성자 자동 추가 -> public Posts(){}
@Entity // JPA 어노테이션 // 테이블과 링크될 클래스임을 나타냄 // 맵핑룰: SaleesManager.java -> sales_manager table
public class Posts {
    @Id // 해당 테이들의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Spring Boot 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto increment가 됨
    private Long id; // @Entity의 PK는 항상 Long 형식의 auto increment로 하는 습관을 들이자.
    // Spring Boot 1.5와 2.0의 버전 차이: https://jojoldu.tistory.com/295

    @Column(length = 500, nullable = false) // 컬럼에 대한 선언 //굳이 선언하지 않아도 모든 필드는 컬럼이 됨 // 문자열 길이: 500, NOT NULL
    private String title;
    // default 문자열은 VARCHAR(255)

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
