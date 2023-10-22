package pulleydoreurae.chwijunjindan.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pulleydoreurae.chwijunjindan.domain.UserAccount;
import pulleydoreurae.chwijunjindan.repository.UserAccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자 [" + username +  "] 의 정보를 찾을 수 없습니다. 아이디 혹은 비밀번호를 확인해주세요."));

        return new CustomUserDetails(userAccount);
    }
}
