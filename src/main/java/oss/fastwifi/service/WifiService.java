package oss.fastwifi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oss.fastwifi.FastwifiApplication;
import oss.fastwifi.entity.Building;
import oss.fastwifi.entity.Wifi;
import oss.fastwifi.repository.BuildingRepository;
import oss.fastwifi.repository.WifiRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WifiService {

    private final BuildingRepository buildingRepository;
    private final WifiRepository wifiRepository;

    public List<Wifi> getWifiList(String building, int floor){
        Building findBuilding = buildingRepository.findByNameAndFloor(building, floor).get();
        return  wifiRepository.findByBuilding(findBuilding);
    }

}
