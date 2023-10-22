package pulleydoreurae.chwijunjindan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pulleydoreurae.chwijunjindan.domain.UserAccount;
import pulleydoreurae.chwijunjindan.exception.CannotRegisterException;
import pulleydoreurae.chwijunjindan.repository.UserAccountRepository;

import java.util.Optional;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public void register(UserAccount userAccount) throws CannotRegisterException {   // 회원가입 메서드
        Optional<UserAccount> result = userAccountRepository.findById(userAccount.getUserId());

        if(result.isEmpty()) {  // 회원정보가 없다면 회원가입
            userAccountRepository.save(userAccount);
        }
        else {  // 회원정보가 있다면 예외던지기
            throw new CannotRegisterException("이미 존재하는 아이디입니다.");
        }
    }
}
