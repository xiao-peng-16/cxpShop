package com.cxp.shop_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cxp.shop_springboot.mapper")
@SpringBootApplication
public class ShopSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopSpringbootApplication.class, args);
    }

}
