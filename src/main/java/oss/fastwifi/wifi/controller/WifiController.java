package oss.fastwifi.wifi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import oss.fastwifi.common.ResponseDto;
import oss.fastwifi.member.entity.Member;
import oss.fastwifi.wifi.dto.request.BuildingReq;
import oss.fastwifi.wifi.dto.request.UnitReq;
import oss.fastwifi.wifi.dto.request.WifiReq;
import oss.fastwifi.wifi.dto.response.WifiForListRes;
import oss.fastwifi.wifi.dto.response.WifiInfoRes;
import oss.fastwifi.wifi.dto.response.WifiPwdRes;
import oss.fastwifi.wifi.entity.Building;
import oss.fastwifi.wifi.service.WifiService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class WifiController {

    private final WifiService wifiService;

    @PostMapping("/floors")
    public ResponseEntity<List<Integer>> floor(
            @RequestBody BuildingReq building
            ){
        List<Integer> floorListInfo = wifiService.getFloorListInfo(building.getBuildingName());

        return ResponseDto.ok(floorListInfo);
    }

    @PostMapping("/wifiList")
    public ResponseEntity<List<WifiForListRes>> buildingAndFloor(
            @RequestBody @Valid UnitReq unitReq
            ){
        List<WifiForListRes> wifiList = wifiService
                .getWifiListWithoutPwd(unitReq.getBuildingName(),unitReq.getFloor());

        return ResponseDto.ok(wifiList);
    }

    @PostMapping("/wifi")
    public ResponseEntity<WifiInfoRes> wifiInfoWithoutPwd(
            @RequestBody @Valid WifiReq wifiReq
            ){
        WifiInfoRes wifiList = wifiService
                .getWifiInfoWithoutPwd(wifiReq.getBuildingName(), wifiReq.getFloor(), wifiReq.getWifiName());

        return ResponseDto.ok(wifiList);
    }

    @PostMapping("/pwd")
    public ResponseEntity<WifiPwdRes> requestPwd(
            @RequestBody @Valid WifiReq wifiReq, @AuthenticationPrincipal Member member
    ){
        wifiService.checkSchoolCertification(member.getSchoolCertification());
        WifiPwdRes pwd = wifiService
                .getWifiPwd(wifiReq.getBuildingName(), wifiReq.getFloor(), wifiReq.getWifiName());

        return ResponseDto.ok(pwd);
    }
}
