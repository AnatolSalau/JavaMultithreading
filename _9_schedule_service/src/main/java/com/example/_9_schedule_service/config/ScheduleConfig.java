package com.example._9_schedule_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class ScheduleConfig {

      @Bean
      public RestTemplate restTemplate() {
            return new RestTemplate();
      }

      @Bean
      public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
            ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
            threadPoolTaskScheduler.setPoolSize(5);
            threadPoolTaskScheduler.setThreadNamePrefix("Scheduler-");
            return threadPoolTaskScheduler;
      }
}
