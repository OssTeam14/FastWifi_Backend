package oss.fastwifi.verification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import oss.fastwifi.common.RedisService;
import oss.fastwifi.error.exception.BusinessException;
import oss.fastwifi.member.entity.Member;
import oss.fastwifi.member.repository.MemberRepository;
import oss.fastwifi.verification.dto.VerifyingType;
import oss.fastwifi.verification.dto.request.*;
import oss.fastwifi.error.dto.ErrorCode;

import java.time.Duration;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VerificationService {
    private static final String VERIFIED_FLAG = "VERIFIED";
    private static final long VERIFICATION_CODE_VALID_MINUTE = 10;
    private static final long VERIFIED_FLAG_VALID_MINUTE = 20;
    private static final int VERIFICATION_CODE_LENGTH = 6;

    private final RedisService redisService;
    private final MailService mailService;
    private final MemberRepository userRepository;

    public void signUpCode(SignUpCodeReq signUpCodeReq) {
        try {
            sendVerifiedCodeByEmail(signUpCodeReq.getEmail(), VerifyingType.SIGN_UP);
        } catch (Exception e) {
            log.error("error while sending verification code: {}", e);
        }
    }

    public void findIdCode(FindIdCodeReq findIdCodeReq) {
        String name = findIdCodeReq.getName();
        String email = findIdCodeReq.getEmail();

        if (!userRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.UNEXISTING_USER);
        }

        try {
            sendVerifiedCodeByEmail(email, VerifyingType.FIND_ID);
        } catch (Exception e) {
            log.error("error while sending verification code: {}", e);
        }
    }

    public void findPwCode(FindPwCodeReq findPwCodeReq) {
        String uid = findPwCodeReq.getUid();
        String email = findPwCodeReq.getEmail();

        if (!userRepository.existsByEmail(uid)) {
            throw new BusinessException(ErrorCode.UNEXISTING_ID);
        }

        Member member = userRepository.findByEmail(uid).get();
        if (!email.equals(member.getEmail())) {
            throw new BusinessException(ErrorCode.UNMATCHING_PHONE_NUM);
        }

        try {
            sendVerifiedCodeByEmail(email, VerifyingType.FIND_PW);
        } catch (Exception e) {
            log.error("error while sending verification code: {}", e);
        }
    }

    public void verify(VerifyReq verifyReq) {
        String email = verifyReq.getEmail();
        String requestCode = verifyReq.getCode();
        VerifyingType verifyingType = verifyReq.getVerifyingType();
        String redisKey = verifyingType + email;

        if (!redisService.hasKey(redisKey)) {
            throw new BusinessException(ErrorCode.EXPIRED_VERIFICATION_CODE);
        }

        String verificationCode = redisService.getValues(redisKey);
        if (!verificationCode.equals(requestCode)) {
            throw new BusinessException(ErrorCode.INCORRECT_VERIFICATION_CODE);
        }

        redisService.setValues(redisKey, VERIFIED_FLAG, Duration.ofMinutes(VERIFIED_FLAG_VALID_MINUTE));
    }

    public void validateIsVerified(String email, VerifyingType verifyingType) {
        String redisKey = verifyingType + email;
        String flag = redisService.getValues(redisKey);

        if (flag == null || !flag.equals(VERIFIED_FLAG)) {
            throw new BusinessException(ErrorCode.NEED_VERIFICATION);
        }

        redisService.deleteValues(redisKey);
    }

    public void sendVerifiedCodeByEmail(String email, VerifyingType verifyingType){
        String verificationCode = makeRandomNumber();

        log.info("receiver Email: {}", email);
        log.info("verification code: {}", verificationCode);
        log.info("verification type: {}", verifyingType);

        mailService.sendEmail(email, VERIFICATION_CODE_VALID_MINUTE, verificationCode);

        redisService.setValues(verifyingType + email, verificationCode, Duration.ofMinutes(VERIFICATION_CODE_VALID_MINUTE));
    }

    private String makeRandomNumber() {
        Random rand = new Random();
        String numStr = "";
        for(int i=0; i<VERIFICATION_CODE_LENGTH; i++) {
            String num = Integer.toString(rand.nextInt(10));
            numStr += num;
        }
        return numStr;
    }

}
