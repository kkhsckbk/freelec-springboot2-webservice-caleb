package org.example.caleb.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.caleb.springboot.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
//    @GetMapping("/")
//    public String index(){
//        return "index"; // 머스테치 스타터가 앞의 경로는 src/main/resources/templates/ 뒤의 확장자는 .mustache가 자동으로 지정
//    }
    @GetMapping("/")
    public String index(Model model) { // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
        model.addAttribute("posts", postsService.findAllDesc()); // postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
        return "index";
    }
    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save"; // 위의 주소가 호출되면 posts-save.mustache가 호출됨
    }

    private final PostsService postsService; // @RequiredArgsConstructor를 필요로 함


}
