package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.LocalTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Daytime timeDependentBean() {
        return isDaytime() ? new Day() : new Night();
    }

    public static boolean isDaytime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(LocalTime.of(6, 0)) && currentTime.isBefore(LocalTime.of(22, 0));
    }
    // END
}
