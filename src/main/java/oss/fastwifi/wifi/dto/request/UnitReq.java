package oss.fastwifi.wifi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UnitReq {
    @NotBlank(message = "건물 이름이 필요합니다.")
    private String buildingName;

    @NotBlank(message = "층 정보가 필요합니다.")
    private Integer floor;
}
