package com.payworks.test;

import com.payworks.test.config.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@ComponentScan
@SpringBootApplication
public class PayworksTestApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PayworksTestApplication.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);

        addDefaultProfile(app, source);
        app.run(args);
    }

    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active")) {
            app.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
        }
    }
}
