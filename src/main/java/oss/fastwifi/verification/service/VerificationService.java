package oss.fastwifi.verification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import oss.fastwifi.common.RedisService;
import oss.fastwifi.error.exception.BusinessException;
import oss.fastwifi.user.entity.User;
import oss.fastwifi.user.repository.UserRepository;
import oss.fastwifi.verification.dto.VerifyingType;
import oss.fastwifi.verification.dto.request.*;
import oss.fastwifi.error.dto.ErrorCode;
import oss.fastwifi.verification.dto.response.NaverSmsRes;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VerificationService {
    private static final String VERIFIED_FLAG = "VERIFIED";
    private static final long VERIFICATION_CODE_VALID_MINUTE = 10;

    private static final int VERIFICATION_CODE_LENGTH = 6;

    private final RedisService redisService;
    private final MailService mailService;
    private final UserRepository userRepository;

    public void signUpCode(SignUpCodeReq signUpCodeReq) {
        try {
            sendVerifiedCodeByEmail(signUpCodeReq.getPhoneNum(), VerifyingType.SIGN_UP);
        } catch (Exception e) {
            log.error("error while sending verification code: {}", e);
        }
    }

    public void findIdCode(FindIdCodeReq findIdCodeReq) {
        String name = findIdCodeReq.getName();
        String phoneNum = findIdCodeReq.getPhoneNum();

        if (!userRepository.existsByNameAndPhoneNum(name,phoneNum)) {
            throw new BusinessException(ErrorCode.UNEXISTING_USER);
        }

        try {
            sendVerifiedCodeByEmail(phoneNum, VerifyingType.FIND_ID);
        } catch (Exception e) {
            log.error("error while sending verification code: {}", e);
        }
    }

    public void findPwCode(FindPwCodeReq findPwCodeReq) {
        String uid = findPwCodeReq.getUid();
        String phoneNum = findPwCodeReq.getPhoneNum();

        if (!userRepository.existsByUid(uid)) {
            throw new BusinessException(ErrorCode.UNEXISTING_ID);
        }

        User user = userRepository.findByUid(uid).get();
        if (!phoneNum.equals(user.getPhoneNum())) {
            throw new BusinessException(ErrorCode.UNMATCHING_PHONE_NUM);
        }

        try {
            sendSms(phoneNum, VerifyingType.FIND_PW);
        } catch (Exception e) {
            log.error("error while sending verification code: {}", e);
        }
    }

    public void verify(VerifyReq verifyReq) {
        String phoneNum = verifyReq.getPhoneNum();
        String requestCode = verifyReq.getCode();
        VerifyingType verifyingType = verifyReq.getVerifyingType();
        String redisKey = verifyingType + phoneNum;

        if (!redisService.hasKey(redisKey)) {
            throw new BusinessException(ErrorCode.EXPIRED_VERIFICATION_CODE);
        }

        String verificationCode = redisService.getValues(redisKey);
        if (!verificationCode.equals(requestCode)) {
            throw new BusinessException(ErrorCode.INCORRECT_VERIFICATION_CODE);
        }

        redisService.setValues(redisKey, VERIFIED_FLAG, Duration.ofMinutes(VERIFIED_FLAG_VALID_MINUTE));
    }

    public void validateIsVerified(String phoneNum, VerifyingType verifyingType) {
        String redisKey = verifyingType + phoneNum;
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
