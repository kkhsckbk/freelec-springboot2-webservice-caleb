package org.example.caleb.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정. 항상 프로젝트 상단에 위치해야 함
public class Application1{
    public static void main(String[] args){
        SpringApplication.run(Application1.class, args); // 내장 WAS를 실행
    }
}
