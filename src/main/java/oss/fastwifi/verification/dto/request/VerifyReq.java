package oss.fastwifi.verification.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import oss.fastwifi.verification.dto.VerifyingType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static oss.fastwifi.common.Constants.MAIL_REGEXP;

@Getter
@NoArgsConstructor
public class VerifyReq {
    @NotBlank
    @Pattern(regexp = MAIL_REGEXP, message = "유효하지 않은 메일 주소입니다.")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\d{6}$", message = "인증번호는 6자리의 숫자로만 입력 가능합니다.")
    private String code;

    @NotNull
    private VerifyingType verifyingType;
}
