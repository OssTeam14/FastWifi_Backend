package oss.fastwifi.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import oss.fastwifi.dto.entity.Wifi;
import oss.fastwifi.dto.response.WifiWithPwdDTO;
import oss.fastwifi.dto.response.WifiWithoutPwdDTO;


public class WifiMapperTest {

    private WifiMapper wifiMapper = Mappers.getMapper(WifiMapper.class);

    @Test
    void 매핑테스트_패스워드포함(){
        //given
        Wifi sampleWifi = Wifi.builder()
                .name("새빛1")
                .password("12345678")
                .downloadSpeed(100)
                .uploadSpeed(10)
                .build();

        //when
        WifiWithPwdDTO resultWithPwd = wifiMapper.toWifiWithPwdDto(sampleWifi);

        //then
        Assertions.assertThat(resultWithPwd.getName()).isEqualTo(sampleWifi.getName());
        Assertions.assertThat(resultWithPwd.getPassword()).isEqualTo(sampleWifi.getPassword());
        Assertions.assertThat(resultWithPwd.getDownloadSpeed()).isEqualTo(sampleWifi.getDownloadSpeed());
        Assertions.assertThat(resultWithPwd.getUploadSpeed()).isEqualTo(sampleWifi.getUploadSpeed());

    }

    @Test
    void 매핑테스트_패스워드미포함(){
        //given
        Wifi sampleWifi = Wifi.builder()
                .name("새빛1")
                .password("12345678")
                .downloadSpeed(100)
                .uploadSpeed(10)
                .build();

        //when
        WifiWithoutPwdDTO resultWithOutPwd = wifiMapper.toWifiWithoutPwdDto(sampleWifi);

        //then
        Assertions.assertThat(resultWithOutPwd.getName()).isEqualTo(sampleWifi.getName());
        Assertions.assertThat(resultWithOutPwd.getDownloadSpeed()).isEqualTo(sampleWifi.getDownloadSpeed());
        Assertions.assertThat(resultWithOutPwd.getUploadSpeed()).isEqualTo(sampleWifi.getUploadSpeed());

    }
}
