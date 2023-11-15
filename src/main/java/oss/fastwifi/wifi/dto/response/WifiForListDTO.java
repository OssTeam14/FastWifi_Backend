package oss.fastwifi.wifi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import oss.fastwifi.wifi.dto.enums.WifiSpeed;

@Getter
@Setter
@Builder
public class WifiForListDTO {
    private String name;
    private WifiSpeed speed;
}
