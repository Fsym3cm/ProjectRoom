package com.chengzimm.dataAnalysis;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chengzimm.dataAnalysis.mapper")
public class DataAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataAnalysisApplication.class, args);
    }
}
