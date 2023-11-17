package oss.fastwifi.wifi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oss.fastwifi.wifi.dto.WifiMapper;
import oss.fastwifi.wifi.dto.response.WifiForListRes;
import oss.fastwifi.wifi.dto.response.WifiPwdRes;
import oss.fastwifi.wifi.dto.response.WifiInfoRes;
import oss.fastwifi.wifi.repository.WifiRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WifiService {
    private final WifiRepository wifiRepository;
    private final WifiMapper wifiMapper;

    public List<WifiForListRes> getWifiListWithoutPwd(String building, int floor){
        List<WifiForListRes> wifiList = wifiRepository.findAllByBuilding_NameAndBuilding_Floor(building, floor)
                .stream()
                .map(wifiMapper::toWifiForListDto)
                .collect(Collectors.toList());

        return wifiList;
    }

    public WifiPwdRes getWifiInfoWithPwd(String buildingName, int floor, String wifiName){

        return wifiMapper.toWifiPwdRes(
                wifiRepository
                        .findByBuilding_NameAndBuilding_FloorAndAndName(buildingName, floor, wifiName)
                        .orElse(null)
        );
    }

    public WifiInfoRes getWifiInfoWithoutPwd(String buildingName, int floor, String wifiName){

        return wifiMapper.from(
                wifiRepository
                        .findByBuilding_NameAndBuilding_FloorAndAndName(buildingName, floor, wifiName)
                        .orElse(null)
        );
    }

}
