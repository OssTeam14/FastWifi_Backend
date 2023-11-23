package oss.fastwifi.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import oss.fastwifi.wifi.dto.WifiMapper;
import oss.fastwifi.wifi.dto.response.WifiInfoRes;
import oss.fastwifi.wifi.entity.Wifi;
import oss.fastwifi.wifi.dto.response.WifiPwdRes;


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
        WifiPwdRes resultWithPwd = wifiMapper.toWifiPwdRes(sampleWifi);

        //then
        Assertions.assertThat(resultWithPwd.getName()).isEqualTo(sampleWifi.getName());
        Assertions.assertThat(resultWithPwd.getPassword()).isEqualTo(sampleWifi.getPassword());

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
        WifiInfoRes resultWithOutPwd = wifiMapper.from(sampleWifi);

        //then
        Assertions.assertThat(resultWithOutPwd.getWifiName()).isEqualTo(sampleWifi.getName());
        Assertions.assertThat(resultWithOutPwd.getDownloadSpeed()).isEqualTo(sampleWifi.getDownloadSpeed());
        Assertions.assertThat(resultWithOutPwd.getUploadSpeed()).isEqualTo(sampleWifi.getUploadSpeed());

    }
}
