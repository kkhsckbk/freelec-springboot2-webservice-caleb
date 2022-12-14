package org.example.caleb.springboot.web;

import org.example.caleb.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
//import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.bind.annotation.GetMapping;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest에서는 CustomOAuth2UserService를 스캔하지 않음
// @WebMvcTest는 WebSecurityConfigurerAdapter, WebMvcConfigurer를 비롯한 @ControllerAdvice, @Controller를 읽음
// 즉, @Repository, @Service, @Component는 스캔 대상이 아님
// 즉, SecurityConfig는 읽었지만 SecurityConfig를 생성하기 위해 필요한 CustomOAuth2UserService는 읽을 수가 없어 에러가 발생
@RunWith(SpringRunner.class) // 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다. 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 수행
@WebMvcTest(controllers = HelloController.class, // Web (Spring MVC)에 집중할 수 있는 어노테이션. 선언하면 @Controller, @ControllerAdvice 등을 사용 가능. 여기서는 컨트롤러만 사용
        excludeFilters={ // 스캔 대상에서 SecurityConfig를 제거
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = SecurityConfig.class)
        }
)// @WebMvcTest를 사용하는 경우, JPA 기능이 작동하지 않음
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 Bean을 주입 받음
    private MockMvc mvc; // 웹 API를 테스트 // 스프링 MVC의 테스트 시작점 // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스팅이 가능

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello")) // 해당 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // mvc.perform()의 결과를 검증
                .andExpect(content().string(hello)); // mvc.perform()의 결과를 검증. 응답 본문의 내용을 검증
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;
        mvc.perform(
                    get("/hello/dto")
                        .param("name", name) // param(): API를 테스트할 때 사용될 요청 파라미터 값을 설정. 단, 값은 String만 허용되어 하기와 같은 변환이 필요
                        .param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name))) // jsonPath(): JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
