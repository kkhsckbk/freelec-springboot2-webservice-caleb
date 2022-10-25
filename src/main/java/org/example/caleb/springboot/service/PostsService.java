package org.example.caleb.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.caleb.springboot.domain.posts.Posts;
import org.example.caleb.springboot.domain.posts.PostsRepository;
import org.example.caleb.springboot.web.dto.PostsListResponseDto;
import org.example.caleb.springboot.web.dto.PostsResponseDto;
import org.example.caleb.springboot.web.dto.PostsSaveRequestDto;
import org.example.caleb.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional; // readOnly 옵션을 허용하지 않음

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // Transactional
    // 설명: 데이터베이스의 상태를 변경하는 작업 또는 한번에 수행되어야 하는 연산들을 의미
    //      begin, commit을 자동으로 수행
    //      예외 발생 시 rollback 처리를 자동으로 수행
    // 특성:
    //      원자성(Atomicity): 한 트랜잭션 내에서 실행한 작업들은 하나의 단위로 처리(즉, 모두 성공 혹은 실패)
    //      일관성(Consistency): 트랜잭션은 일관성 있는 데이터베이스 상태를 유지한다.(data integrity 만족 등)
    //      격리성(Isolation): 동시에 실행되는 트랜잭션들이 서로 영향을 미치지 않도록 격리해야 한다.
    //      영속성(Durability): 트랜잭션을 성공적으로 마치면 결과가 항상 저장되어야 한다.
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // update를 날리는 query가 없음.-> JPA의 영속성 컨텍스트 때문
    // 영속성 컨텍스트:
    //      엔티티를 영구 저장하는 환경
    //      JPA의 Entity Manager가 활성화된 상태로(Spring Data JPA를 사용하면 기본 옵션) 트랜잭션 안에서 데이터베이스의 데이터를 가져오면, 이 데이터는 영속성 컨텍스트가 유지된 상태
    //      이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
    //      즉, Entity 객체의 값만 변경하면 Update 쿼리를 날릴 필요가 없다. -> "Dirty checking"이라고 함.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    // listing
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly=true) // readOnly=true는 트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회 속도가 개선(등록/수정/삭제가 전혀 없는 경우 사용)
    public List<PostsListResponseDto> findAllDesc(){
        // postsRepository 결과로 넘어온 posts의 stream을 map을 통해 PostsListResponseDto로 변환 -> List로 반환하는 메소드
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListResponseDto::new) // .map(PostsListResponseDto::new) === .map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList()); // PostsRepository.interface 에 List<Posts>로 저장되어 있음
    }
}
