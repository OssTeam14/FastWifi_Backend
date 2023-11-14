package oss.fastwifi.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oss.fastwifi.entity.Building;
import oss.fastwifi.entity.Wifi;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FastWifiRepositoryTest {

    @Autowired
    WifiRepository fastWifiRepository;
    @Autowired
    BuildingRepository buildingRepository;

    @Test
    @DisplayName("저장")
    public void save(){
        //Given
        Building sebit = buildingRepository.findByNameAndFloor("새빛관", 1).get();
        Wifi wifi = Wifi.builder()
                .name("새빛1")
                .downloadSpeed(100)
                .uploadSpeed(10)
                .building(sebit)
                .build();

        //When
        Wifi save = fastWifiRepository.save(wifi);

        //Then
        assertThat(save).isEqualTo(wifi);


    }

    @Test
    public void 건물이름과층으로조회(){
        //Given
        Building sebit = buildingRepository.findByNameAndFloor("새빛관",1).get();
        for (int i = 0; i < 10; i++) {
            Wifi wifi = Wifi.builder()
                    .name("새빛" + i)
                    .downloadSpeed(100)
                    .uploadSpeed(10)
                    .building(sebit)
                    .build();
            fastWifiRepository.save(wifi);
        }

        //when
        List<Wifi> wifis = fastWifiRepository.findByBuilding(sebit);

        assertThat(wifis.size()).isEqualTo(10);


    }

}