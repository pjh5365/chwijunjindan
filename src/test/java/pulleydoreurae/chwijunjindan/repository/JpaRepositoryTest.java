package pulleydoreurae.chwijunjindan.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pulleydoreurae.chwijunjindan.domain.UserAccount;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest    // Jpa 관련 테스트 설정만 로드해준다. 내부적으로 @ExtendWith(SpringExtension.class)를 가지고 있다.
@DisplayName("JPA Repository 테스트")
class JpaRepositoryTest {

    private final UserAccountRepository userAccountRepository;

    @BeforeEach
    public void beforeEach() {
        // Given
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId("testId");
        userAccount.setUserPassword("testPW");

        userAccountRepository.save(userAccount);
    }

    @Autowired
    JpaRepositoryTest(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Test
    @DisplayName("데이터 삽입 테스트")
    void insertAndSelectTest() {
        // Given

        // When
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId("testId2");
        userAccount.setUserPassword("testPW2");

        // Then
        assertThat(userAccountRepository.save(userAccount)).isEqualTo(userAccount);
        assertThat(userAccountRepository.findById("testId2")).isEqualTo(Optional.of(userAccount));

        System.out.println("저장된 회원정보 출력");
        System.out.println(userAccountRepository.findAll());
    }

    @Test
    @DisplayName("데이터 업데이트 테스트")
    void updateTest() {
        // Given

        // When
        UserAccount userAccountUpdate = userAccountRepository.findById("testId").get();
        userAccountUpdate.setUserPassword("updatePW");
        userAccountRepository.save(userAccountUpdate);

        // Then
        assertThat(userAccountRepository.findById("testId")).isEqualTo(Optional.of(userAccountUpdate));

        System.out.println("저장된 회원정보 출력");
        System.out.println(userAccountRepository.findAll());
    }

    @Test
    @DisplayName("데이터 삭제 테스트")
    void deleteTest() {
        // Given

        // When
        userAccountRepository.deleteById("testId");

        // Then
        assertThat(userAccountRepository.findById("testId")).isEmpty();

        System.out.println("저장된 회원정보 출력");
        System.out.println(userAccountRepository.findAll());
    }
}
