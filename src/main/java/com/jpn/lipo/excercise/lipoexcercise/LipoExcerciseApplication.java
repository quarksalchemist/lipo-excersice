package com.jpn.lipo.excercise.lipoexcercise;

import com.jpn.lipo.excercise.lipoexcercise.repository.CurrentPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@Configuration
@SpringBootApplication(scanBasePackages={
        "com.jpn.lipo.excercise.lipoexcercise.service", "com.jpn.lipo.excercise.lipoexcercise.controller", "com.jpn.lipo.excercise.lipoexcercise.repositorys",
        "com.jpn.lipo.excercise.lipoexcercise.pojo"})
public class LipoExcerciseApplication {

    private static final Logger log = LoggerFactory.getLogger(LipoExcerciseApplication.class);

    public static void main(String[] args) {
        log.debug("Initializating app...");
        SpringApplication.run(LipoExcerciseApplication.class, args);
    }

    @Bean
    JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    CurrentPriceRepository stringRedisRepository(StringRedisTemplate template) {
        return new CurrentPriceRepository(template);
    }



}
