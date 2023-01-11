package org.example.caleb.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.caleb.springboot.service.PostsService;
import org.example.caleb.springboot.web.dto.PostsResponseDto;
import org.example.caleb.springboot.web.dto.PostsSaveRequestDto;
import org.example.caleb.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // Bean을 주입받음
@RestController
class PostApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto); // 요청받은 DTO를 그대로 전달 // https://techblog.woowahan.com/2711/
    }

    //update
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    };

    //delete
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
    //list
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}