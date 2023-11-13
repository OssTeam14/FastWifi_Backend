package oss.fastwifi.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wifi {

    @Id
    @GeneratedValue
    @Column
    private Long id;
    private String name;
    private Integer downloadSpeed;
    private Integer uploadSpeed;
    private LocalDate lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @Builder
    public Wifi(String name, Integer downloadSpeed, Integer uploadSpeed, LocalDate lastUpdate, Building building) {
        this.name = name;
        this.downloadSpeed = downloadSpeed;
        this.uploadSpeed = uploadSpeed;
        this.lastUpdate = lastUpdate;
        this.building = building;
    }
}
