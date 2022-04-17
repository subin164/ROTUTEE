package com.greedy.rotutee.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.greedy.rotutee")
@EnableJpaRepositories(basePackages = "com.greedy.rotutee")
public class JPAConfiguration {
}
