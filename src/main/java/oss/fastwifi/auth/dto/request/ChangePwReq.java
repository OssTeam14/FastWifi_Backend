package oss.fastwifi.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static oss.fastwifi.common.Constants.*;

@Getter
@NoArgsConstructor
public class ChangePwReq {
    @NotBlank
    @Pattern(regexp = MAIL_REGEXP, message = "유효하지 않은 메일 주소입니다.")
    private String email;

    @NotBlank
    private String uid;

    @NotBlank
    @Pattern(regexp = PW_REGEXP, message = "비밀번호는 8~16 자리의 영문 & 숫자 조합으로만 입력 가능합니다.")
    private String newPassword;

    @NotBlank
    @Pattern(regexp = PW_REGEXP, message = "비밀번호는 8~16 자리의 영문 & 숫자 조합으로만 입력 가능합니다.")
    private String confirmPassword;
}
