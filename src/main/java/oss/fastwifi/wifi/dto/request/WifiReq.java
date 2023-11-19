package oss.fastwifi.wifi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WifiReq {
    @NotBlank(message = "건물 이름이 필요합니다.")
    private String buildingName;

    @NotNull(message = "층 정보가 필요합니다.")
    private Integer floor;

    @NotBlank(message = "와이파이 이름이 필요합니다")
    private String wifiName;
}
