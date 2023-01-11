package org.example.caleb.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.caleb.springboot.config.auth.LoginUser;
import org.example.caleb.springboot.config.auth.dto.SessionUser;
import org.example.caleb.springboot.service.PostsService;
import org.example.caleb.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
//    @GetMapping("/")
//    public String index(){
//        return "index"; // 머스테치 스타터가 앞의 경로는 src/main/resources/templates/ 뒤의 확장자는 .mustache가 자동으로 지정
//    }

    private final PostsService postsService; // @RequiredArgsConstructor를 필요로 함
//    @GetMapping("/")
//    public String index(Model model) { // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
//        model.addAttribute("posts", postsService.findAllDesc()); // postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
//        return "index";
//    }

    private final HttpSession httpSession;
    @GetMapping("/")
    //public String index(Model model){
    public String index(Model model, @LoginUser SessionUser user){ // 기존에 (User) httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선 // 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있게 됨
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        // 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있음
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user!=null){ // 세션에 저장된 값이 있을 때만 model에 userName으로 등록 // 세션에 저장된 값이 없는 경우, 로그인 버튼이 보임
            model.addAttribute("username", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save"; // 위의 주소가 호출되면 posts-save.mustache가 호출됨
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    };



}
