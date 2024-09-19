package com.rsing.recipe.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        return httpSecurity.authorizeHttpRequests(request->
//                request.requestMatchers("/h2-console/**").permitAll()
//                        .anyRequest().authenticated())
//                .headers(headers -> headers.frameOptions(Customizer.withDefaults()))
//                .csrf(AbstractHttpConfigurer::disable).build();
//    }
}
