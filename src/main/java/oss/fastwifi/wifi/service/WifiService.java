package oss.fastwifi.wifi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oss.fastwifi.error.dto.ErrorCode;
import oss.fastwifi.error.exception.BusinessException;
import oss.fastwifi.user.entity.SchoolCertification;
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

    public WifiPwdRes getWifiPwd(String buildingName, int floor, String wifiName){

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

    public void checkSchoolCertification(SchoolCertification authType){
        if(authType.equals(SchoolCertification.REFUSAL)){
            throw new BusinessException(ErrorCode.EXPIRED_VERIFICATION_CODE);
        }
    }

}
