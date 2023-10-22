package pulleydoreurae.chwijunjindan.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pulleydoreurae.chwijunjindan.domain.UserAccount;
import pulleydoreurae.chwijunjindan.exception.CannotRegisterException;
import pulleydoreurae.chwijunjindan.repository.UserAccountRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class) // @Mock 어노테이션을 보고 mock 객체를 주입해 mock 객체를 어노테이션으로 사용할 수 있게 해주는 어노테이션
@DisplayName("회원관리 서비스 테스트")
class UserAccountServiceTest {

    @InjectMocks    // @Mock에서 만들어진 객체를 자동으로 주입해준다.
    private UserAccountService userAccountService;
    @Mock
    private UserAccountRepository userAccountRepository;

    @Test
    @DisplayName("회원가입에 대한 테스트")
    void registerFirst() throws CannotRegisterException {
        // Given
        UserAccount account = new UserAccount();
        account.setUserId("testID");
        account.setUserPassword("testPW");

        // Mocking 한 아이디의 최초의 회원가입이라면
        given(userAccountRepository.findById(account.getUserId())).willReturn(Optional.empty());

        // When

        // Then
        userAccountService.register(account);   // 회원가입이 정상작동 된다면 예외가 발생하지 않는다.

    }

    @Test
    @DisplayName("회원가입 중복 테스트")
    void registerSecond() {
        // Given
        UserAccount account = new UserAccount();
        account.setUserId("testID");
        account.setUserPassword("testPW");

        // Mocking 한 아이디의 두번째 회원가입 시도일 경우
        given(userAccountRepository.findById(account.getUserId())).willReturn(Optional.of(account));

        // When

        // Then
        // 같은 아이디로 회원가입을 요청하면 CannotRegisterException 예외를 발생시킨다.
        assertThatThrownBy(() -> userAccountService.register(account)).isInstanceOf(CannotRegisterException.class);
    }
}
