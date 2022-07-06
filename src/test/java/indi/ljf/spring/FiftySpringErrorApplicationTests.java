package indi.ljf.spring;

import indi.ljf.spring.service.ElectricService;
import indi.ljf.spring.service.LightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FiftySpringErrorApplicationTests {
    @Autowired
    private ElectricService electricService;
    @Test
    void contextLoads() {
        System.out.println(electricService);
        try {
            electricService.charge();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
