package org.example.caleb.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 보통 ibastis나 MyBatis 등에서 Dao라고 불리는 DB Layer 접근자
// JPA에서는 Repository라고 부르며 인터페이스로 생성
// [중요] Entity 클래스와 기본 Entity Repository는 함께 위치해야 함
public interface PostsRepository extends JpaRepository<Posts, Long>{
    // SpringDataJpa에서 제공하지 않는 메소드는 하기처럼 쿼리로 작성
    // 규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건 등으로 인해 이런 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용
    // 대표적으로, querydsl, jooq, MyBatis 등이 있음
    // 타입의 안정성, 많은 회사에서 사용 중, 레퍼런스가 많음의 이유로 querydsl을 추천
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc(); // findAllDesc()를 호출하면 Posts의 배열로 결과값 반환
}
