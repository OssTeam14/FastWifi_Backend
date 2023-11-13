package oss.fastwifi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class WifiInfo {

    @Id
    @GeneratedValue
    @Column
    private Long id;
    private Integer floor;
    private Integer downloadSpeed;
    private Integer uploadSpeed;
    private LocalDate lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;
}
