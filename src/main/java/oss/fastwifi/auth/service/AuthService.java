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
import oss.fastwifi.error.dto.ErrorCode;
import oss.fastwifi.error.exception.BusinessException;
import oss.fastwifi.user.entity.User;
import oss.fastwifi.user.repository.UserRepository;
import oss.fastwifi.verification.dto.VerifyingType;
import oss.fastwifi.verification.service.VerificationService;
import oss.fastwifi.jwt.JwtTokenProvider;
import oss.fastwifi.jwt.RefreshToken;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final VerificationService verificationService;

    @Transactional
    public void signUp(SignUpReq signUpReq) {
        verificationService.validateIsVerified(signUpReq.getPhoneNum(), VerifyingType.SIGN_UP);

        if(!checkUidAvailable(signUpReq.getUid())) {
            throw new BusinessException(ErrorCode.ID_ALREADY_EXISTS);
        }

        userRepository.save(User.builder()
                .uid(signUpReq.getUid())
                .password(passwordEncoder.encode(signUpReq.getPassword()))
                .email(signUpReq.getPhoneNum())
                .name(signUpReq.getName())
                .build());
    }

    public TokenRes login(LoginReq loginReq) {
        User user = userRepository.findByUid(loginReq.getUid()).orElseThrow(() -> new BusinessException(ErrorCode.WRONG_ID));
        if (!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.WRONG_PW);
        }
        TokenRes token = TokenRes.builder()
                .accessToken(jwtTokenProvider.createAccessToken(user.getId()))
                .refreshToken(jwtTokenProvider.createRefreshToken(user.getId()))
                .build();
        return token;
    }

    public boolean checkUidAvailable(String uid) {
        return !userRepository.existsByUid(uid);
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
        String name = findIdReq.getName();

        verificationService.validateIsVerified(email, VerifyingType.FIND_ID);

        List<User> users = userRepository.findAllByNameAndEmail(name,email);
        List<String> uids = users.stream().map(User::getUid).collect(Collectors.toList());

        return FindIdRes.builder()
                .uids(uids)
                .build();
    }

    @Transactional
    public void changePw(ChangePwReq changePwReq) {
        String email = changePwReq.getEmail();
        String uid = changePwReq.getUid();
        String newPassword = changePwReq.getNewPassword();
        String confirmPassword = changePwReq.getConfirmPassword();

        verificationService.validateIsVerified(email, VerifyingType.FIND_PW);
        validateNewPassword(newPassword,confirmPassword);

        User user = userRepository.findByUid(uid).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        user.updatePassword(passwordEncoder.encode(newPassword));
    }

    private void validateNewPassword(String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new BusinessException(ErrorCode.UNMATCHING_NEW_PASSWORD);
        }
    }
}
