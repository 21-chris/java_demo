//package com.example.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//
///**
// * SpringSecurity 5.4.x以上新用法配置
// * 为避免循环依赖，仅用于配置HttpSecurity
// * Created by macro on 2022/5/19.
// */
//@Configuration
//public class SecurityConfig {
//
////    @Bean
////    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
////        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
////                .authorizeRequests();
////        //不需要保护的资源路径允许访问
//////        for (String url : ignoreUrlsConfig.getUrls()) {
//////            registry.antMatchers(url).permitAll();
//////        }
////        //省略HttpSecurity的配置
////        return httpSecurity.build();
////    }
//
//}
