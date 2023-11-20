package oss.fastwifi.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import oss.fastwifi.auth.dto.request.*;
import oss.fastwifi.auth.dto.response.AccessTokenRes;
import oss.fastwifi.auth.dto.response.FindIdRes;
import oss.fastwifi.auth.dto.response.TokenRes;
import oss.fastwifi.auth.repository.RefreshTokenRedisRepository;
import oss.fastwifi.common.Constants;
import oss.fastwifi.error.dto.ErrorCode;
import oss.fastwifi.error.exception.BusinessException;
import oss.fastwifi.member.entity.Member;
import oss.fastwifi.member.entity.SchoolCertification;
import oss.fastwifi.member.repository.MemberRepository;
import oss.fastwifi.verification.dto.VerifyingType;
import oss.fastwifi.verification.service.VerificationService;
import oss.fastwifi.jwt.JwtTokenProvider;
import oss.fastwifi.jwt.RefreshToken;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final VerificationService verificationService;

    @Transactional
    public void signUp(SignUpReq signUpReq) {
        verificationService.validateIsVerified(signUpReq.getEmail(), VerifyingType.SIGN_UP);

        if(!checkEmailAvailable(signUpReq.getEmail())) {
            throw new BusinessException(ErrorCode.ID_ALREADY_EXISTS);
        }

        SchoolCertification schoolCertification = SchoolCertification.REFUSAL;

        if(Pattern.matches(Constants.KW_MAIL_REGEXP, signUpReq.getEmail())){
            schoolCertification = SchoolCertification.APPROVED;
        }

        memberRepository.save(Member.builder()
                .password(passwordEncoder.encode(signUpReq.getPassword()))
                .email(signUpReq.getEmail())
                .schoolCertification(schoolCertification)
                .build());
    }

    public TokenRes login(LoginReq loginReq) {
        Member member = memberRepository.findByEmail(loginReq.getEmail()).orElseThrow(() -> new BusinessException(ErrorCode.WRONG_ID));
        if (!passwordEncoder.matches(loginReq.getPassword(), member.getPassword())) {
            throw new BusinessException(ErrorCode.WRONG_PW);
        }
        TokenRes token = TokenRes.builder()
                .accessToken(jwtTokenProvider.createAccessToken(member.getId()))
                .refreshToken(jwtTokenProvider.createRefreshToken(member.getId()))
                .build();
        return token;
    }

    public boolean checkEmailAvailable(String email) {
        return !memberRepository.existsByEmail(email);
    }

    public AccessTokenRes refresh(RefreshReq refreshReq) {
        String requestToken = refreshReq.getRefreshToken();

        jwtTokenProvider.validateRefreshToken(requestToken);

        RefreshToken redisToken = refreshTokenRedisRepository.findById(requestToken).orElseThrow(() -> new BusinessException(ErrorCode.INVALID_JWT_TOKEN));
        if ((redisToken.getUserId() != Long.parseLong(jwtTokenProvider.getUserId(requestToken)))) {
            throw new BusinessException(ErrorCode.INVALID_JWT_TOKEN);
        }

        AccessTokenRes newAccessToken = AccessTokenRes.builder()
                .accessToken(jwtTokenProvider.createAccessToken(redisToken.getUserId()))
                .build();
        return newAccessToken;
    }

    public void logout(Long userId) {
        RefreshToken token = refreshTokenRedisRepository.findByUserId(userId);
        refreshTokenRedisRepository.deleteById(token.getRefreshToken());
    }

    public FindIdRes findId(FindIdReq findIdReq) {
        String email = findIdReq.getEmail();

        verificationService.validateIsVerified(email, VerifyingType.FIND_ID);

        List<Member> members = memberRepository.findAllByEmail(email);
        List<String> emailList = members.stream().map(Member::getEmail).collect(Collectors.toList());

        return FindIdRes.builder()
                .uids(emailList)
                .build();
    }

    @Transactional
    public void changePw(ChangePwReq changePwReq) {
        String email = changePwReq.getEmail();
        String uid = changePwReq.getEmail();
        String newPassword = changePwReq.getNewPassword();
        String confirmPassword = changePwReq.getConfirmPassword();

        verificationService.validateIsVerified(email, VerifyingType.FIND_PW);
        validateNewPassword(newPassword,confirmPassword);

        Member member = memberRepository.findByEmail(uid).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        member.updatePassword(passwordEncoder.encode(newPassword));
    }

    private void validateNewPassword(String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new BusinessException(ErrorCode.UNMATCHING_NEW_PASSWORD);
        }
    }
}
