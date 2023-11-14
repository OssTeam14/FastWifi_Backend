package oss.fastwifi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oss.fastwifi.entity.Building;
import oss.fastwifi.entity.Wifi;

import java.util.List;
import java.util.Optional;

@Repository
public interface WifiRepository extends JpaRepository<Wifi, Long> {
    Optional<Wifi> findById(Long id);
    List<Wifi> findByBuilding(Building building);
    Wifi save(Wifi wifi);

}
