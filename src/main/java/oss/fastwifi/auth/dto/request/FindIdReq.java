package oss.fastwifi.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static oss.fastwifi.common.Constants.PHONE_NUM_REGEXP;

@Getter
@Setter
@NoArgsConstructor
public class FindIdReq {
    @NotBlank
    @Pattern(regexp = PHONE_NUM_REGEXP, message = "휴대폰 번호는 10~11자리의 숫자로만 입력 가능합니다.")
    private String phoneNum;

    @NotBlank
    private String name;
}
