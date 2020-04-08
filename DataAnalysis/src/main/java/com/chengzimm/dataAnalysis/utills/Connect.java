package com.chengzimm.dataAnalysis.utills;

import weka.core.Instances;
import weka.experiment.InstanceQuery;
import java.sql.Connection;


public class Connect {
    public static Connection conn(String sql){
        Connection conn = null;
        String dbURL = "jdbc:mysql://localhost:3306/hla?DatabaseName=hla&characterEncoding=utf8&serverTimezone=UTC&autoReconnect=true&useSSL=false";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
        String Name = "root";
        String Pwd = "123456";
        try {
            InstanceQuery query = new InstanceQuery();
            query.setDatabaseURL(dbURL); // 链接数据库
            query.setUsername(Name);
            query.setPassword(Pwd);
            query.setQuery(sql);
            Instances dataset = query.retrieveInstances();
            System.out.println(dataset.toString()); // 打印数据集
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败");
        }
        return conn;
    }
}


