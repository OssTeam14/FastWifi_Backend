package oss.fastwifi.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oss.fastwifi.entity.Building;
import oss.fastwifi.entity.Wifi;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class FastWifiRepositoryTest {

    @Autowired
    WifiRepository fastWifiRepository;
    @Autowired
    BuildingRepository buildingRepository;

    @Test
    public void 건물이름과층으로조회(){
        //Given
        List<Building> sebitList = buildingRepository.findByName("새빛관");
        List<Building> chambitList = buildingRepository.findByName("참빛관");


        Wifi wifi = new Wifi();

    }

}