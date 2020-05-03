package com.chengzimm.dataAnalysis.dao;

import com.alibaba.fastjson.JSON;
import com.chengzimm.dataAnalysis.utills.Connect;
import com.chengzimm.dataAnalysis.model.DataCollect;
import weka.classifiers.functions.LinearRegression;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataDao {

    public List<DataCollect> query(String dataId, String federationId, String memberId, Double time) {
        List<DataCollect> datas = new LinkedList<>();
        Pattern pattern = Pattern.compile("\\?");
        String sql = "SELECT output_value, step FROM `data_collect?` where federation_id = ? and member_id = ? and time = ?";
        StringBuffer buffer = new StringBuffer();
        Matcher matcher = pattern.matcher(sql);
        int i = 0;
        while (matcher.find()) {
            if (i == 0){
                matcher.appendReplacement(buffer, dataId);
            } else if (i == 1){
                matcher.appendReplacement(buffer, federationId);
            } else if (i == 2){
                matcher.appendReplacement(buffer, memberId);
            }else {
                matcher.appendReplacement(buffer, time.toString());
            }
            i++;
        }
        matcher.appendTail(buffer);
        System.out.println(buffer.toString());
        Connect.conn(buffer.toString());
        return datas;
    }

    public List<DataCollect> queryByDataId(String dataId, String memberId1, String memberId2) {
        List<DataCollect> datas = new LinkedList<>();
        Pattern pattern = Pattern.compile("\\?");
        String sql = "SELECT max( CASE WHEN member_id = ? THEN output_value ELSE 0  END)  AS out0, max( CASE WHEN member_id = ? THEN output_value ELSE 0 END) AS out1 FROM data_collect? GROUP BY step;";
        StringBuffer buffer = new StringBuffer();
        Matcher matcher = pattern.matcher(sql);
        int i = 0;
        while (matcher.find()) {
            if (i == 0) {
                matcher.appendReplacement(buffer, memberId1);
            }else if (i == 1){
                matcher.appendReplacement(buffer, memberId2);
            } else {
                matcher.appendReplacement(buffer, dataId);
            }
            i++;
        }
        matcher.appendTail(buffer);
        System.out.println(buffer.toString());
        Connect.conn(buffer.toString());
        return datas;
    }

    public static void main(String[] args) {

        DataDao dataDao = new DataDao();
     /*   dataDao.query("", "101","1" ,1.0);
        System.out.println(Connect.dataset.toString());
        dataDao.queryByDataId("", "1", 1.0);
        System.out.println(Connect.dataset.toString());*/
        dataDao.queryByDataId("", "1", "2");
        System.out.println(Connect.dataset.toString());
    }
}
