package com.isometric;

import com.isometric.entity.ID;
import com.isometric.entity.User;
import com.isometric.repository.IDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@ComponentScan(basePackages = {"com.isometric", "it.ozimov.springboot"})
@EnableAsync
public class IsometricBackEndApplication implements CommandLineRunner {

    @Autowired
    private IDRepository idRepository;
    public static Set<BigInteger> activeUsers = new HashSet<BigInteger>();

    public static void main(String[] args) {
        SpringApplication.run(IsometricBackEndApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (idRepository.count() == 0) {
            idRepository.save(new ID("key", BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(1)));
        }
    }
}
