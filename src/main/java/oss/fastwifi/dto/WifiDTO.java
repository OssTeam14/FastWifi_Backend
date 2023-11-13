package oss.fastwifi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import oss.fastwifi.entity.Building;
import oss.fastwifi.entity.Wifi;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class WifiDTO {
    private String name;
    private Integer downloadSpeed;
    private Integer uploadSpeed;
    private LocalDate lastUpdate;
    private Building building;

    public Wifi toEntity(){
        return Wifi.builder()
                .name(name)
                .downloadSpeed(downloadSpeed)
                .uploadSpeed(uploadSpeed)
                .lastUpdate(lastUpdate)
                .building(building)
                .build();
    }
}
