package oss.fastwifi.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static oss.fastwifi.common.Constants.*;

@Getter
@NoArgsConstructor
public class SignUpReq {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = ID_REGEXP, message = "아이디는 6~15 자리의 영문 & 숫자 조합으로만 입력 가능합니다.")
    private String uid;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = PW_REGEXP, message = "비밀번호는 8~16 자리의 영문 & 숫자 조합으로만 입력 가능합니다.")
    private String password;

    @NotBlank
    @Pattern(regexp = MAIL_REGEXP, message = "유효하지 않은 메일 주소입니다.")
    private String phoneNum;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
}