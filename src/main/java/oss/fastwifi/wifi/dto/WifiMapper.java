package oss.fastwifi.wifi.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oss.fastwifi.wifi.dto.response.WifiForListRes;
import oss.fastwifi.wifi.entity.Wifi;
import oss.fastwifi.wifi.dto.enums.WifiSpeed;
import oss.fastwifi.wifi.dto.response.WifiWithPwdRes;
import oss.fastwifi.wifi.dto.response.WifiWithoutPwdRes;

@Mapper(componentModel = "spring")
public interface WifiMapper {
    WifiWithoutPwdRes toWifiWithoutPwdDto(Wifi wifi);
    WifiWithPwdRes toWifiWithPwdDto(Wifi wifi);

    @Mapping(source = "downloadSpeed", target = "speed")
    WifiForListRes toWifiForListDto(Wifi wifi);

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
