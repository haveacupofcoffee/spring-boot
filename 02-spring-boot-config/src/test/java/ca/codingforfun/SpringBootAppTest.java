package ca.codingforfun;

import ca.codingforfun.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAppTest {

    @Autowired
    private Person person;

    @Test
    public void contextLoad() {
        System.out.println(person);
    }
}