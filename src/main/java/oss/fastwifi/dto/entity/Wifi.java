package oss.fastwifi.dto.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wifi {

    @Id
    @GeneratedValue
    @Column
    private Long id;
    private String name;
    private String password;
    private Integer downloadSpeed;
    private Integer uploadSpeed;
    private LocalDate lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

}
