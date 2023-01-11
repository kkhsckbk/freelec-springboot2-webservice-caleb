package org.example.caleb.springboot.web.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
Spring에서 Bean을 주입받는 방식
1. @Autowired(권장하지 않는 방식)
2. setter
3. 생성자(권장): @RequiredArgsConstructor를 정의
 */
@Getter // 선언된 모든 필드의 get 메소드를 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함한 생성자를 생성 // final이 없는 필드는 생성자에 포함되지 않음
public class HelloResponseDto{
    private final String name;
    private final int amount;
}
