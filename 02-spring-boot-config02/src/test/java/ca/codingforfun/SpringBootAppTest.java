package ca.codingforfun;

import ca.codingforfun.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAppTest {

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext ioc;


    @Test
    public void contextLoad() {
        System.out.println(person);
    }

    @Test
    public void containsBean() {
        //if no @ImportResource annotation to import spring xml configuration, then return false
        //assertFalse(ioc.containsBean("personService"));

        //after add @ImportResource on main class, then return true
        assertTrue(ioc.containsBean("personService"));
    }
}