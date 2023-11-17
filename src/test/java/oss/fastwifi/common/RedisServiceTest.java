package oss.fastwifi.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    void setValues(){
        redisService.setValues("test", "test");
        redisService.deleteValues("test");
    }

}