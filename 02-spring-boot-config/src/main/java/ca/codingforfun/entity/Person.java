package ca.codingforfun.entity;

import ca.codingforfun.entity.Pet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * before 2.2, when use @ConfigurationProperties, has to add @Component or @Configuration to declare this is a Spring Bean
 * 2.2.0, added new annotation @ConfigurationPropertiesScan, and enabled by default , could scan all the property classes in classpath, in this
 * version, don't need to add @Component or @Configuration
 * {@link https://github.com/spring-projects/spring-boot/issues/16612}
 *
 * 2.2.1, disabled by default, needs to be explicitly opted into by adding the @ConfigurationPropertiesScan annotation
 *
 * @Value annotation like the value attribute in spring xml configuration in
 * <bean class="Person">
 *     <property name="firstName" value="value | ${key} | #{SpEL}"></property>
 * </bean>
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
@Validated // used for JSR303 validation
public class Person {
    //@Value("${person.first-name}")
    private String firstName;
    private String lastName;
    //@Value("#{3*3}")
    private int age;
    //@Value("true")
    private Boolean boss;
    private Date birth;
    @Email  // not support by @Value
    private String email;
    private Map<String, Object> maps;
    private List<Object> lists;

    private Pet pet;


}
