package oss.fastwifi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import oss.fastwifi.dto.enums.WifiSpeed;

@Getter
@Setter
@Builder
public class WifiForListDTO {
    private String name;
    private WifiSpeed speed;
}
