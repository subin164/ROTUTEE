package com.greedy.rotutee.config;

import com.greedy.rotutee.Authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;
import java.util.Map;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    @Autowired
    public SpringSecurityConfiguration(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "images/**");   // 이녀석들한테는 인증을 요구하지 않겠다
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        Map<String, List<String>> permitListMap = authenticationService.getPermitListMap();
        List<String> adminPermitList = permitListMap.get("adminPermitList");
        List<String> subAdminPermitList = permitListMap.get("subAdminPermitList");
        List<String> tuteePermitList = permitListMap.get("tuteePermitList");
        List<String> tutorPermitList = permitListMap.get("tutorPermitList");
        List<String> memberPermitList = permitListMap.get("memberPermitList");

        adminPermitList.forEach(url -> System.out.println("admin permit url = " + url));
        memberPermitList.forEach(url -> System.out.println("member permit url = " + url));
        tuteePermitList.forEach(url -> System.out.println("tutee permit url = " + url));
        tutorPermitList.forEach(url -> System.out.println("tutor permit url = " + url));
        subAdminPermitList.forEach(url -> System.out.println("subAdmin permit url = " + url));

        http.csrf().disable() // 머시깽이 토큰 공격을 하는걸 막는게 csrf() 이녀석이고 그걸 비활성화 하겠다 왜냐면 활성화하면 로그인할 때 마다 무언가 토큰을 적어줘야한다
                .authorizeHttpRequests()
                .antMatchers(subAdminPermitList.toArray(new String[subAdminPermitList.size()])).hasAnyRole("SUBADMIN", "ADMIN")
                .antMatchers(tuteePermitList.toArray(new String[tuteePermitList.size()])).hasAnyRole("TUTEE", "SUBADMIN", "ADMIN")
                .antMatchers(tutorPermitList.toArray(new String[tutorPermitList.size()])).hasAnyRole("TUTOR", "SUBADMIN", "ADMIN")
                .antMatchers(memberPermitList.toArray(new String[memberPermitList.size()])).hasAnyRole("MEMBER", "TUTEE", "TUTOR", "SUBADMIN", "ADMIN")
                .antMatchers(adminPermitList.toArray(new String[adminPermitList.size()])).hasRole("ADMIN")
                .anyRequest().permitAll()  // 모든 요청에 접근 허용을 하겠다
                /*
                .antMatchers("/member/login").permitAll()  // 이녀석은 일단 아무한테나 보여주게끔 하겠다
                .antMatchers("/**").authenticated()  // 모든 url 요청은 인증을 받아야 사용할 수 있다
                */
                .and()
                .formLogin()  // form 이용한 로그인을 하겠다 활성화
                .loginPage("/member/login")  // 이 url 로그인 form을 쓰겠다
                .successForwardUrl("/")  // 성공시 이곳으로 가겠다
                .failureForwardUrl("/error/login")  // 실패시 이곳으로 가겠다
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))  // 이 요청이 들어오면 로그아웃을 하겠다
                .deleteCookies("JSESSIONID")  // 성공하게 되면 JSESSIONID 라는 쿠키를 삭제 시킬 것
                .invalidateHttpSession(true)  // 세션정보를 무효화시키겠다
                .logoutSuccessUrl("/")  // 성공시 이곳으로 가겠다
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* 인증을 할 때 이 클래스를 사용하겠다 */
        auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
    }


}
