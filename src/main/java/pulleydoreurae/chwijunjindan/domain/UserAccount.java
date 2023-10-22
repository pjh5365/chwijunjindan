package pulleydoreurae.chwijunjindan.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserAccount {

    @Id
    private String userId;
    private String userPassword;

}
