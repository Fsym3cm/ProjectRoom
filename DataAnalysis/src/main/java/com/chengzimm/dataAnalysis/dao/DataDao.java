package com.chengzimm.dataAnalysis.dao;

import com.chengzimm.dataAnalysis.connection.Connect;
import com.chengzimm.dataAnalysis.model.DataCollect;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataDao {

    public List<DataCollect> query(String federationId, String memberId, Double time) {
        List<DataCollect> datas = new LinkedList<>();
        Pattern pattern = Pattern.compile("\\?");
        String sql = "SELECT output_value, step FROM `data_collect` where federation_id = ? and member_id = ? and time = ?";
        StringBuffer buffer = new StringBuffer();
        Matcher matcher = pattern.matcher(sql);
        int i = 0;
        while (matcher.find()) {
            if (i == 0){
                matcher.appendReplacement(buffer, federationId);
            } else if (i == 1){
                matcher.appendReplacement(buffer, memberId);
            } else {
                matcher.appendReplacement(buffer, time.toString());
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
        dataDao.query("1", "1", 1.0);
    }
}
