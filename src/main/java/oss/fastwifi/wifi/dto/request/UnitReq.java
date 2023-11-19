package oss.fastwifi.wifi.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnitReq {
    @NotBlank(message = "건물 이름이 필요합니다.")
    private String buildingName;

    @NotNull(message = "층 정보가 필요합니다.")
    private Integer floor;
}
