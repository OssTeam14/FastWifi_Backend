package oss.fastwifi.wifi.dto;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import oss.fastwifi.wifi.dto.response.WifiForListDTO;
import oss.fastwifi.wifi.dto.response.WifiForListDTO.WifiForListDTOBuilder;
import oss.fastwifi.wifi.dto.response.WifiWithPwdDTO;
import oss.fastwifi.wifi.dto.response.WifiWithPwdDTO.WifiWithPwdDTOBuilder;
import oss.fastwifi.wifi.dto.response.WifiWithoutPwdDTO;
import oss.fastwifi.wifi.dto.response.WifiWithoutPwdDTO.WifiWithoutPwdDTOBuilder;
import oss.fastwifi.wifi.entity.Wifi;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-16T01:40:43+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class WifiMapperImpl implements WifiMapper {

    @Override
    public WifiWithoutPwdDTO toWifiWithoutPwdDto(Wifi wifi) {
        if ( wifi == null ) {
            return null;
        }

        WifiWithoutPwdDTOBuilder wifiWithoutPwdDTO = WifiWithoutPwdDTO.builder();

        wifiWithoutPwdDTO.name( wifi.getName() );
        wifiWithoutPwdDTO.downloadSpeed( wifi.getDownloadSpeed() );
        wifiWithoutPwdDTO.uploadSpeed( wifi.getUploadSpeed() );
        wifiWithoutPwdDTO.lastUpdate( wifi.getLastUpdate() );
        wifiWithoutPwdDTO.building( wifi.getBuilding() );

        return wifiWithoutPwdDTO.build();
    }

    @Override
    public WifiWithPwdDTO toWifiWithPwdDto(Wifi wifi) {
        if ( wifi == null ) {
            return null;
        }

        WifiWithPwdDTOBuilder wifiWithPwdDTO = WifiWithPwdDTO.builder();

        wifiWithPwdDTO.name( wifi.getName() );
        wifiWithPwdDTO.password( wifi.getPassword() );
        wifiWithPwdDTO.downloadSpeed( wifi.getDownloadSpeed() );
        wifiWithPwdDTO.uploadSpeed( wifi.getUploadSpeed() );
        wifiWithPwdDTO.lastUpdate( wifi.getLastUpdate() );
        wifiWithPwdDTO.building( wifi.getBuilding() );

        return wifiWithPwdDTO.build();
    }

    @Override
    public WifiForListDTO toWifiForListDto(Wifi wifi) {
        if ( wifi == null ) {
            return null;
        }

        WifiForListDTOBuilder wifiForListDTO = WifiForListDTO.builder();

        if ( wifi.getDownloadSpeed() != null ) {
            wifiForListDTO.speed( toWifiSpeed( wifi.getDownloadSpeed().intValue() ) );
        }
        wifiForListDTO.name( wifi.getName() );

        return wifiForListDTO.build();
    }
}
