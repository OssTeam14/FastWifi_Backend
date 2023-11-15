package oss.fastwifi.wifi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oss.fastwifi.wifi.dto.response.WifiForListDTO;
import oss.fastwifi.wifi.dto.response.WifiWithoutPwdDTO;
import oss.fastwifi.wifi.response.Response;
import oss.fastwifi.wifi.response.StatusEnum;
import oss.fastwifi.wifi.service.WifiService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/wifi")
public class WifiController {

    private final WifiService wifiService;

    @GetMapping("/{building}/{floor}")
    public ResponseEntity<Response> buildingAndFloor(
            @PathVariable String building,
            @PathVariable int floor
    ){
        List<WifiForListDTO> wifiList = wifiService.getWifiListWithoutPwd(building, floor);

        Response body = Response.builder()
                .status(StatusEnum.OK)
                .data(wifiList)
                .message(building + floor + "층 와이파이 정보")
                .build();

        return new ResponseEntity<>(body,Response.getDefaultHeader(), HttpStatus.OK);
    }

    @GetMapping("/{building}/{floor}/{wifiName}")
    public ResponseEntity<Response> wifiInfoWithoutPwd(
            @PathVariable String building,
            @PathVariable int floor,
            @PathVariable String wifiName
    ){
        WifiWithoutPwdDTO wifiList = wifiService.getWifiInfoWithoutPwd(building, floor, wifiName);

        Response body = Response.builder()
                .status(StatusEnum.OK)
                .data(wifiList)
                .message(building + floor + "층 " + wifiName + " 와이파이 정보")
                .build();

        return new ResponseEntity<>(body,Response.getDefaultHeader(), HttpStatus.OK);
    }
}
