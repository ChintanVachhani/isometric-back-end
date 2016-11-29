package com.isometric;

import com.isometric.entity.ID;
import com.isometric.repository.IDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;

@SpringBootApplication
public class IsometricBackEndApplication implements CommandLineRunner {

    @Autowired
    private IDRepository idRepository;

    public static void main(String[] args) {
        SpringApplication.run(IsometricBackEndApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (idRepository.count() == 0) {
            idRepository.save(new ID("key", BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(1)));
        }
    }

    /*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("isometric/login").allowedOrigins("http://localhost:63343");
            }
        };
    }*/
}
