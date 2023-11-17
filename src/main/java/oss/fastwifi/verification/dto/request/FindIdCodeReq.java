package oss.fastwifi.verification.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static oss.fastwifi.common.Constants.PHONE_NUM_REGEXP;

@Getter
@NoArgsConstructor
public class FindIdCodeReq {
    @NotBlank
    @Pattern(regexp = PHONE_NUM_REGEXP, message = "휴대폰 번호는 10~11자리의 숫자로만 입력 가능합니다.")
    private String phoneNum;

    @NotBlank
    private String name;
}
