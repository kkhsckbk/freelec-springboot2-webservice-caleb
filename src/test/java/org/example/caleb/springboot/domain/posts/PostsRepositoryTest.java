package org.example.caleb.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정없이 사용할 경우, H2 데이터베이스를 자동으로 실행
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After // Junit 단위 테스트가 끝날 때마다 수행 // 테스트간 데이터 침범을 막기 위해 널리 사용
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("kkhsckbk@gmail.com")
                .build());
        //테이블 posts에 insert/update 쿼리를 실행 (id값이 있다면 update가, 없다면 insert가 실행)

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts =postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}