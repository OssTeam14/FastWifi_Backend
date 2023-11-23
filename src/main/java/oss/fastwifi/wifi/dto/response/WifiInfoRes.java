package oss.fastwifi.wifi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import oss.fastwifi.wifi.entity.Building;

import java.time.LocalDate;

@Getter
@Builder
public class WifiInfoRes {
    private String wifiName;
    private Integer downloadSpeed;
    private Integer uploadSpeed;
    private String lastUpdate;
}
