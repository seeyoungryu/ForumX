package com.example.forumx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing //JPA Auditing 기능 추가
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("testname");  // TODO : 스프링 시큐리티 인증기능 추가시, 수정해야함 , 현재는 인증기능이 없어서 식별 안되는 상태
    }
}

