package oss.fastwifi.verification.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oss.fastwifi.common.ResponseDto;
import oss.fastwifi.verification.dto.request.FindIdCodeReq;
import oss.fastwifi.verification.dto.request.FindPwCodeReq;
import oss.fastwifi.verification.dto.request.SignUpCodeReq;
import oss.fastwifi.verification.dto.request.VerifyReq;
import oss.fastwifi.verification.service.VerificationService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/verification")
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/sign-up-code")
    public ResponseEntity<String> signUpCode(@RequestBody @Valid SignUpCodeReq signUpCodeReq) {
        verificationService.signUpCode(signUpCodeReq);
        return ResponseDto.ok("인증번호 발송 완료");
    }

    @PostMapping("/find-id-code")
    public ResponseEntity<String> findIdCode(@RequestBody @Valid FindIdCodeReq findIdCodeReq) {
        verificationService.findIdCode(findIdCodeReq);
        return ResponseDto.ok("인증번호 발송 완료");
    }

    @PostMapping("/find-pw-code")
    public ResponseEntity<String> findPwCode(@RequestBody @Valid FindPwCodeReq findPwCodeReq) {
        verificationService.findPwCode(findPwCodeReq);
        return ResponseDto.ok("인증번호 발송 완료");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody @Valid VerifyReq verifyReq) {
        verificationService.verify(verifyReq);
        return ResponseDto.ok("인증되었습니다.");
    }
}
