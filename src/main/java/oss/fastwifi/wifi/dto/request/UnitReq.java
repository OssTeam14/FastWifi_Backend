package oss.fastwifi.wifi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UnitReq {
    @NotBlank(message = "건물 이름이 필요합니다.")
    private String buildingName;

    @NotBlank(message = "층 정보가 필요합니다.")
    private Integer floor;
}
