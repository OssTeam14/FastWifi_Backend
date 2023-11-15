package oss.fastwifi.wifi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oss.fastwifi.wifi.entity.Building;
import oss.fastwifi.wifi.entity.Wifi;

import java.util.List;
import java.util.Optional;

@Repository
public interface WifiRepository extends JpaRepository<Wifi, Long> {
    Optional<Wifi> findById(Long id);
    List<Wifi> findAllByBuilding(Building building);
    List<Wifi> findAllByBuilding_NameAndBuilding_Floor(String name, int floor);
    Optional<Wifi> findByBuilding_NameAndBuilding_FloorAndAndName(String building, int floor, String name);

    Wifi save(Wifi wifi);

}
