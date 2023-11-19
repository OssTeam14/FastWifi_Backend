package oss.fastwifi.wifi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingReq {
    @NotBlank(message = "건물 이름이 필요합니다.")
    private String buildingName;
}
