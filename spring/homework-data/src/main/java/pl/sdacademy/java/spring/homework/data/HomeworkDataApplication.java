package pl.sdacademy.java.spring.homework.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
public class HomeworkDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkDataApplication.class, args);
    }

    //Zegar systemowy UTC
    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
