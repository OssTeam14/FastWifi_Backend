package oss.fastwifi.wifi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import oss.fastwifi.wifi.dto.enums.WifiSpeed;

@Getter
@Builder
public class WifiForListRes {
    private String name;
    private WifiSpeed speed;
}
