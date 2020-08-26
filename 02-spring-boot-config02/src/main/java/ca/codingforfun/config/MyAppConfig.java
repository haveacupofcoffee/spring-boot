package ca.codingforfun.config;

import ca.codingforfun.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {

    @Bean
    public PersonService personService02() {
        return new PersonService();
    }
}
