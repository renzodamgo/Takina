package com.takina.userservice.cofig;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Getter
@EnableJpaAuditing
@Configuration
public class UserConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}