package oss.fastwifi.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oss.fastwifi.wifi.entity.Building;
import oss.fastwifi.wifi.entity.Wifi;
import oss.fastwifi.wifi.repository.BuildingRepository;
import oss.fastwifi.wifi.repository.WifiRepository;

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
    public void 건물정보로조회(){
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
        List<Wifi> wifis = fastWifiRepository.findAllByBuilding(sebit);

        assertThat(wifis.size()).isEqualTo(10);


    }

    @Test
    public void 건물정보이름및층으로조회(){
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
        List<Wifi> wifis = fastWifiRepository.findAllByBuilding_NameAndBuilding_Floor("새빛관",1);

        assertThat(wifis.size()).isEqualTo(10);


    }

    @Test
    public void 와이파이정보조회(){
        //Given
        Building sebit = buildingRepository.findByNameAndFloor("새빛관",1).get();
        Wifi sampleWifi = Wifi.builder()
                .name("새빛1")
                .downloadSpeed(100)
                .uploadSpeed(10)
                .building(sebit)
                .build();
        fastWifiRepository.save(sampleWifi);

        //when
        Optional<Wifi> wifi = fastWifiRepository.findByBuilding_NameAndBuilding_FloorAndAndName("새빛관",1, "새빛1");

        //Then
        assertThat(wifi.get()).isEqualTo(sampleWifi);


    }
}