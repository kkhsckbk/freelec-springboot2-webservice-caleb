package org.example.caleb.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// 보통 ibastis나 MyBatis 등에서 Dao라고 불리는 DB Layer 접근자
// JPA에서는 Repository라고 부르며 인터페이스로 생성
// [중요] Entity 클래스와 기본 Entity Repository는 함께 위치해야 함
public interface PostsRepository extends JpaRepository<Posts, Long>{

}
