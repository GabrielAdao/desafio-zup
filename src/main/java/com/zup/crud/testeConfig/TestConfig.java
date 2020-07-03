/*
package com.zup.crud.testeConfig;

import com.zup.crud.entities.User;
import com.zup.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Gabriel", 23 , "45514633888", "gabriel@zup.com", 998082731, "Gioconda Mra" );

        userRepository.saveAll(Arrays.asList(u1));
    }
}
*/
