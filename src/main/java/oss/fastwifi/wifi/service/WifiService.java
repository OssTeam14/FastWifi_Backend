package oss.fastwifi.wifi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oss.fastwifi.wifi.dto.WifiMapper;
import oss.fastwifi.wifi.dto.response.WifiForListDTO;
import oss.fastwifi.wifi.dto.response.WifiWithPwdDTO;
import oss.fastwifi.wifi.dto.response.WifiWithoutPwdDTO;
import oss.fastwifi.wifi.repository.WifiRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WifiService {
    private final WifiRepository wifiRepository;
    private final WifiMapper wifiMapper;

    public List<WifiForListDTO> getWifiListWithoutPwd(String building, int floor){
        List<WifiForListDTO> wifiList = wifiRepository.findAllByBuilding_NameAndBuilding_Floor(building, floor)
                .stream()
                .map(wifiMapper::toWifiForListDto)
                .collect(Collectors.toList());

        return wifiList;
    }

    public WifiWithPwdDTO getWifiInfoWithPwd(String buildingName, int floor, String wifiName){

        return wifiMapper.toWifiWithPwdDto(
                wifiRepository
                        .findByBuilding_NameAndBuilding_FloorAndAndName(buildingName, floor, wifiName)
                        .orElse(null)
        );
    }

    public WifiWithoutPwdDTO getWifiInfoWithoutPwd(String buildingName, int floor, String wifiName){

        return wifiMapper.toWifiWithoutPwdDto(
                wifiRepository
                        .findByBuilding_NameAndBuilding_FloorAndAndName(buildingName, floor, wifiName)
                        .orElse(null)
        );
    }

}
