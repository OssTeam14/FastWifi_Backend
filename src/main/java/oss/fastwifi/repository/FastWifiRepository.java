package oss.fastwifi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oss.fastwifi.entity.WifiInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface FastWifiRepository extends JpaRepository<WifiInfo, Long> {
    Optional<WifiInfo> findById(Long id);
    List<WifiInfo> findByBuilding_Name(String name);
}
