package oss.fastwifi.wifi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WifiReq {
    @NotBlank(message = "건물 이름이 필요합니다.")
    private String buildingName;

    @NotNull(message = "층 정보가 필요합니다.")
    private Integer floor;

    @NotBlank(message = "와이파이 이름이 필요합니다")
    private String wifiName;
}
