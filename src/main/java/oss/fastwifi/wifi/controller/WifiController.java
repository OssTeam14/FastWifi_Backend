package oss.fastwifi.wifi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import oss.fastwifi.common.ResponseDto;
import oss.fastwifi.user.entity.User;
import oss.fastwifi.wifi.dto.request.UnitReq;
import oss.fastwifi.wifi.dto.request.WifiReq;
import oss.fastwifi.wifi.dto.response.WifiForListRes;
import oss.fastwifi.wifi.dto.response.WifiInfoRes;
import oss.fastwifi.wifi.dto.response.WifiPwdRes;
import oss.fastwifi.wifi.service.WifiService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class WifiController {

    private final WifiService wifiService;

    @GetMapping("/wifiList")
    public ResponseEntity<List<WifiForListRes>> buildingAndFloor(
            @RequestBody @Valid UnitReq unitReq
            ){
        List<WifiForListRes> wifiList = wifiService
                .getWifiListWithoutPwd(unitReq.getBuildingName(),unitReq.getFloor());

        return ResponseDto.ok(wifiList);
    }

    @GetMapping("/wifi")
    public ResponseEntity<WifiInfoRes> wifiInfoWithoutPwd(
            @RequestBody @Valid WifiReq wifiReq
            ){
        WifiInfoRes wifiList = wifiService
                .getWifiInfoWithoutPwd(wifiReq.getBuildingName(), wifiReq.getFloor(), wifiReq.getWifiName());

        return ResponseDto.ok(wifiList);
    }

    @GetMapping("/pwd")
    public ResponseEntity<WifiPwdRes> requestPwd(
            @RequestBody @Valid WifiReq wifiReq, @AuthenticationPrincipal User user
    ){
        wifiService.checkSchoolCertification(user.getSchoolCertification());
        WifiPwdRes pwd = wifiService
                .getWifiPwd(wifiReq.getBuildingName(), wifiReq.getFloor(), wifiReq.getWifiName());
        return ResponseDto.ok(pwd);
    }
}
