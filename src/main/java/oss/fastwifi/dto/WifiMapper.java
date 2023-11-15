package oss.fastwifi.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oss.fastwifi.dto.entity.Wifi;
import oss.fastwifi.dto.enums.WifiSpeed;
import oss.fastwifi.dto.response.WifiForListDTO;
import oss.fastwifi.dto.response.WifiWithPwdDTO;
import oss.fastwifi.dto.response.WifiWithoutPwdDTO;

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
