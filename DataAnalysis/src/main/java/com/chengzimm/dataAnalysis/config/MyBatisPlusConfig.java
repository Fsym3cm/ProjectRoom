package com.chengzimm.dataAnalysis.config;

import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

@Configuration
//@MapperScan("com.chengzimm.dataAnalysis.mapper.DataCollectMapper")
public class MyBatisPlusConfig {

    public static Integer number;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        dynamicTableNameParser.setTableNameHandlerMap(new HashMap<String, ITableNameHandler>(2) {{
            put("data_collect", (metaObject, sql, tableName) -> {
                // metaObject 可以获取传入参数，这里实现你自己的动态规则
                String str;
                if (number == 1) {
                    str = "_1";
                }else if (number == 2) {
                    str = "_2";
                }else {
                    str = "";
                }
                return tableName + str;
            });
        }});
        paginationInterceptor.setSqlParserList(Collections.singletonList(dynamicTableNameParser));
        return paginationInterceptor;
    }
}
