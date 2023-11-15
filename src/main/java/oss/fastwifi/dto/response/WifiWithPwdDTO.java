package oss.fastwifi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import oss.fastwifi.entity.Building;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class WifiWithPwdDTO {

    private String name;
    private String password;
    private Integer downloadSpeed;
    private Integer uploadSpeed;
    private LocalDate lastUpdate;
    private Building building;
}
