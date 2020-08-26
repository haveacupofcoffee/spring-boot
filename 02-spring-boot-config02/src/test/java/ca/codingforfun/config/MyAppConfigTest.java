package ca.codingforfun.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyAppConfigTest {

    @Autowired
    private ApplicationContext ioc;

    //test MyAppConfig.java
    @Test
    public void personService02() {
        assertTrue(ioc.containsBean("personService02"));
    }
}