package pulleydoreurae.chwijunjindan.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public CustomAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 로그인 인증 실행 부분
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName(); // 사용자 아이디 가져오기
        String password = authentication.getCredentials().toString();   // 사용자 비밀번호 가져오기

        UserDetails user = userDetailsService.loadUserByUsername(username);
        if(password.matches(user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        }
        else {
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }
    }

    // 객체가 UsernamePasswordAuthenticationToken 클래스 또는 이 클래스의 하위 클래스로 인스턴스화될 수 있는지를 확인 (동작 여부를 제한)
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication);
    }
}
