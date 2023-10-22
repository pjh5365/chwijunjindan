package pulleydoreurae.chwijunjindan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pulleydoreurae.chwijunjindan.domain.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
