package oss.fastwifi.wifi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oss.fastwifi.wifi.entity.Building;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    Optional<Building> findByNameAndFloor(String name, Integer floor);
    List<Building> findByName(String name);
}
