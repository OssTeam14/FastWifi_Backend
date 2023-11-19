package oss.fastwifi.verification.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static oss.fastwifi.common.Constants.MAIL_REGEXP;

@Getter
@NoArgsConstructor
public class FindPwCodeReq {
    @NotBlank
    @Pattern(regexp = MAIL_REGEXP, message = "유효하지 않은 메일 주소입니다.")
    private String email;

    @NotBlank
    private String uid;
}
