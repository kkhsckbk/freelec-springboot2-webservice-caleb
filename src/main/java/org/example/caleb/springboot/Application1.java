package org.example.caleb.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 어노테이션들을 모두 활성화
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정. 항상 프로젝트 상단에 위치해야 함 // @EnableAutoConfiguration, @ComponentScan, @Configuration을 하나로 묶음
public class Application1{
//    System.setProperty("spring.devtools.restart.enabled", "false");
//    System.setProperty("spring.devtools.livereload.enabled", "true");
    public static void main(String[] args){ // SpringBoot를 가동하기 위해 필요
        SpringApplication.run(Application1.class, args); // 내장 WAS를 실행
        }
}