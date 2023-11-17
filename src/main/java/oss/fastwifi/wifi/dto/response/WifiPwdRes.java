package oss.fastwifi.wifi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WifiPwdRes {

    private String name;
    private String password;
}
