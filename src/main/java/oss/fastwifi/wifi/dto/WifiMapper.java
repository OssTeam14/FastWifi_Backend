package oss.fastwifi.wifi.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oss.fastwifi.wifi.entity.Wifi;
import oss.fastwifi.wifi.dto.enums.WifiSpeed;
import oss.fastwifi.wifi.dto.response.WifiForListDTO;
import oss.fastwifi.wifi.dto.response.WifiWithPwdDTO;
import oss.fastwifi.wifi.dto.response.WifiWithoutPwdDTO;

@Mapper(componentModel = "spring")
public interface WifiMapper {
    WifiWithoutPwdDTO toWifiWithoutPwdDto(Wifi wifi);
    WifiWithPwdDTO toWifiWithPwdDto(Wifi wifi);

    @Mapping(source = "downloadSpeed", target = "speed")
    WifiForListDTO toWifiForListDto(Wifi wifi);

    default WifiSpeed toWifiSpeed(int speed) {
        if(speed < 100){
            return WifiSpeed.SLOW;
        } else if (speed < 300) {
            return WifiSpeed.NORMAL;
        } else{
            return WifiSpeed.FAST;
        }
    }
}
