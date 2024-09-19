package com.rsing.recipe.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

//    @Bean
//    public OpenAPI springShopOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("My API")
//                        .description("My API documentation")
//                        .version("3.3.3")
//                        .license(new License().name("Apache 2.0").url("(link unavailable)")));
//    }
}
