package pulleydoreurae.chwijunjindan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pulleydoreurae.chwijunjindan.domain.UserAccount;
import pulleydoreurae.chwijunjindan.exception.CannotRegisterException;
import pulleydoreurae.chwijunjindan.service.UserAccountService;

@Slf4j
@Controller
@RequestMapping("/api")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserAccount userAccount) {
        try {
            userAccountService.register(userAccount);
            log.info("회원가입 성공 [ {} ]", userAccount);

            return "/login";    // 회원가입 성공 후 로그인페이지로 이동
        } catch (CannotRegisterException e) {
            log.info("이미 존재하는 아이디입니다.");

            return "registerError";  // 회원가입 실패시 에러페이지로 이동
        }
    }
}
