package br.com.testesUnitarios.demo.config;

import br.com.testesUnitarios.demo.domain.Users;
import br.com.testesUnitarios.demo.repositories.UserRepositories;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepositories userRepositories;

    @PostConstruct
    public void startDB() {
        Users us1 = new Users(null, "Antonella", "Antonella@email", "1234");
        Users us2 = new Users(null, "Kiara", "Kiara@email", "4567");
        userRepositories.saveAll(List.of(us1, us2));
    }
}
