package pulleydoreurae.chwijunjindan.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pulleydoreurae.chwijunjindan.domain.UserAccount;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
// TODO: 10/22/23 통합테스트 대신 @WebMvcTest로 단위테스트 할 수 있도록 수정하기
//@WebMvcTest(UserAccountController.class)
@DisplayName("회원가입, 로그인 테스트")
class UserAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserAccountController userAccountController;

    @Test
    @DisplayName("회원가입 테스트")
    @WithMockUser   // 에러를 막기위한 기본 권한 부여
    void registerTest() throws Exception {
        // Given

        // When
        mockMvc.perform(post("/api/register")
                        .param("userId", "testingId")
                        .param("userPassword", "testingPW"))

        // Then
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("시큐리티 로그인 성공 테스트")
    void securityLoginSuccessTest() throws Exception {
        // Given
        // 로그인 전 회원가입 부터 실행
        UserAccount user = new UserAccount();
        user.setUserId("pibber");
        user.setUserPassword("wjsansrk");
        userAccountController.register(user);

        // When
        mockMvc.perform(post("/login")
                        .param("username", "pibber")
                        .param("password", "wjsansrk"))

        // Then
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("시큐리티 로그인 실패 테스트")
    void securityLoginFailTest() throws Exception {
        // Given

        // When
        // 회원가입되지않은 아이디와 비밀번호로 로그인 시도
        mockMvc.perform(post("/login")
                .param("username", "asdf")
                .param("password", "aaa"))
        // Then
            .andExpect(redirectedUrl("/login/error"));
    }
}
