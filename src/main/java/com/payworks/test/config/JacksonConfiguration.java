package com.payworks.test.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.payworks.test.util.JSR310LocalDateDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Configuration
public class JacksonConfiguration {

    private final Logger log = LoggerFactory.getLogger(JacksonConfiguration.class);

    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        log.debug("Configuring Jackson mapper");
        JavaTimeModule module = new JavaTimeModule();
        module.addDeserializer(LocalDate.class, JSR310LocalDateDeserializer.INSTANCE);
        return new Jackson2ObjectMapperBuilder()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .findModulesViaServiceLoader(true)
                .modulesToInstall(module);
    }
}
