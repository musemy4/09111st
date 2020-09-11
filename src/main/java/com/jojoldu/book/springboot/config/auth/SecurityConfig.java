package com.jojoldu.book.springboot.config.auth;


import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//security설정들을 활성화시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable()//h2화면을 사용하기 위해 해당 옵션들을 disable한다
                .and().authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작점-antMatchers옵션 사용가능하게함
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())//권한 관리 대상을 지정하는 옵션.url,http메소드별로 관리
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/")//로그아웃 기능에 대한 설정의 진입점. 로그아웃시 우선"/"로간다
                .and().oauth2Login()//로그인기능에 대한 진입점
                    .userInfoEndpoint()//성공이후 사용자정보를 가져올때의 설정
                        .userService(customOAuth2UserService);//성공시 후속조치를 진행할 UserService인터페이스의 구현체를 등록



    }
}
