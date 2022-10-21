package org.example.caleb.springboot.web.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 get 메소드를 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함한 생성자를 생성 // final이 없는 필드는 생성자에 포함되지 않음
public class HelloResponseDto{
    private final String name;
    private final int amount;
}