package oss.fastwifi.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import oss.fastwifi.auth.dto.request.*;
import oss.fastwifi.auth.dto.response.AccessTokenRes;
import oss.fastwifi.auth.dto.response.FindIdRes;
import oss.fastwifi.auth.dto.response.TokenRes;
import oss.fastwifi.auth.service.AuthService;
import oss.fastwifi.auth.service.TokenCookieService;
import oss.fastwifi.common.ResponseDto;
import oss.fastwifi.member.entity.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenCookieService tokenCookieService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpReq signUpReq){
            authService.signUp(signUpReq);
            return ResponseDto.ok("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenRes> login(@RequestBody @Valid LoginReq loginReq, HttpServletResponse response) {
        TokenRes token = authService.login(loginReq);
        tokenCookieService.addCookie(response, token.getAccessToken());
        return ResponseDto.ok(token);
    }

    @GetMapping("/id-available")
    public ResponseEntity<Boolean> idAvailable(@RequestParam String email) {
        return ResponseDto.ok(authService.checkEmailAvailable(email));
    }


    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenRes> refresh(@RequestBody @Valid RefreshReq refreshReq) {
        AccessTokenRes token = authService.refresh(refreshReq);
        return ResponseDto.ok(token);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal Member member, HttpServletResponse response) {
        authService.logout(member.getId());
        tokenCookieService.expireCookie(response);
        return ResponseDto.ok("로그아웃 성공");
    }

    @GetMapping("/find-id")
    public ResponseEntity<FindIdRes> findId(@ModelAttribute @Valid FindIdReq findIdReq) {
        FindIdRes findIdRes = authService.findId(findIdReq);
        return ResponseDto.ok(findIdRes);
    }

    @PostMapping("/change-pw")
    public ResponseEntity<String> changePw(@RequestBody @Valid ChangePwReq changePwReq) {
        authService.changePw(changePwReq);
        return ResponseDto.ok("비밀번호가 재설정 되었습니다.");
    }
}
