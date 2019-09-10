package com.jzt.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.jzt.system.mapper")
public class MybatisConfig {

}
