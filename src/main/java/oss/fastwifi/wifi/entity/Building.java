package oss.fastwifi.wifi.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Building {
    @Id
    @GeneratedValue
    Long id;
    String name;
    Integer floor;

    @Builder
    public Building(String name, Integer floor) {
        this.name = name;
        this.floor = floor;
    }
}
