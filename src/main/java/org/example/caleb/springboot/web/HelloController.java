package org.example.caleb.springboot.web;

import org.example.caleb.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러로 만듦
public class HelloController {
    @GetMapping("/hello") // Get 요청을 받을 수 있는 API 생성
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
    // @RequestParam: 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션.
}
