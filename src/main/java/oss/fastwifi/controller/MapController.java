package oss.fastwifi.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MapController {
    @GetMapping("/map/{building}/{floor}")
    public ResponseEntity<Response> clickBuilding(
            @PathVariable String building, @PathVariable int floor
    ){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
