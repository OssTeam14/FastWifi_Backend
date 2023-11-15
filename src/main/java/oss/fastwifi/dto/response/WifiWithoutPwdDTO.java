package oss.fastwifi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import oss.fastwifi.dto.entity.Building;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class WifiWithoutPwdDTO {
    private String name;
    private Integer downloadSpeed;
    private Integer uploadSpeed;
    private LocalDate lastUpdate;
    private Building building;


}
