package ca.codingforfun.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@PropertySource(value = {"classpath:person.properties"})   // to externalize your configuration to a properties file
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Boolean boss;
    private Date birth;
    private String email;
    private Map<String, Object> maps;
    private List<Object> lists;

    private Pet pet;


}
