package org.example.caleb.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.caleb.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점 // antMatchers 옵션을 사용할 수 있도록 함
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest() // 설정된 값들 이외 나머지 URL들을 나타냄
                .authenticated() // 나머지 URL들은 모두 인증된 사용자들에게만 허용(즉, 로그인한 사용자들)
                .and()
                .logout() // 로그아웃 기능에 대한 여러 설정의 진입점
                .logoutSuccessUrl("/") // 로그아웃 성공 시 "/" 주소로 이동
                .and()
                .oauth2Login() // OAuth2 로그인 기능에 대한 진입점
                .userInfoEndpoint() // Oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록 // 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시
    }
}
