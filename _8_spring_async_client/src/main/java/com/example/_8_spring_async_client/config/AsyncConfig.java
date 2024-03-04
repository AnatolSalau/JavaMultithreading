package com.example._8_spring_async_client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

      @Bean
      public RestTemplate restTemplate() {
            return new RestTemplate();
      }

      @Bean(name = "ourCustomExecutor")
      public Executor asyncExecutor() {
            ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
            threadPoolTaskExecutor.setCorePoolSize(3);
            threadPoolTaskExecutor.setMaxPoolSize(3);
            threadPoolTaskExecutor.setQueueCapacity(100);
            threadPoolTaskExecutor.setThreadNamePrefix("CustomExecutor-");
            threadPoolTaskExecutor.initialize();

            return threadPoolTaskExecutor;
      }
}
