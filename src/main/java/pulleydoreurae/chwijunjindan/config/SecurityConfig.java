package pulleydoreurae.chwijunjindan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("testid")
//                        .password("{noop}pw")
//                        .build()
//        );
//    }

//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .anyRequest().permitAll())  // 우선 모든 접근 허용
                .csrf(csrf -> csrf.disable()) // 일단 csrf끄기, 사용한다면 로그인 및 회원가입은 csrf 때문에 정상적으로 작동하지 않음
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")    // 로그인 페이지는 해당 url을 사용하고
                        .failureUrl("/login/error") // 로그인 실패시 해당 url로 이동
                        .defaultSuccessUrl("/"));   // 로그인이 성공하면 해당 url로 이동

        return http.build();
    }
}
