package oss.fastwifi.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import oss.fastwifi.entity.Building;
import oss.fastwifi.entity.Wifi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BuildingRepositoryTest {

    @Autowired
    WifiRepository fastWifiRepository;
    @Autowired
    BuildingRepository buildingRepository;

    @Test
    @DisplayName("건물 이름으로 조회")
    public void findByName(){
        //Given
        //When
        List<Building> sebitList = buildingRepository.findByName("새빛관");

        //then
        assertThat(sebitList.size()).isEqualTo(4);

    }

    @Test
    @DisplayName("건물 이름과 층으로 조회")
    public void findByNameAndFloor(){
        //Given
        //When
        Building sebit = buildingRepository.findByNameAndFloor("새빛관", 1).get();
        //Then
        assertThat(sebit.getName()).isEqualTo("새빛관");
        assertThat(sebit.getFloor()).isEqualTo(1);
    }
}