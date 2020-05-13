package com.chengzimm.dataAnalysis.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengzimm.dataAnalysis.config.MyBatisPlusConfig;
import com.chengzimm.dataAnalysis.dao.DataDao;
import com.chengzimm.dataAnalysis.mapper.DataCollectMapper;
import com.chengzimm.dataAnalysis.model.DataCollect;
import com.chengzimm.dataAnalysis.service.DataAnalysisService;
import com.chengzimm.dataAnalysis.utills.Connect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.functions.LinearRegression;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {

    DataDao dataDao = new DataDao();

    @Autowired
    private DataCollectMapper dataCollectMapper;

    @Override
    public String linearRegression(String elementId1, String elementId2, String dataId) {
        dataDao.queryByDataId(dataId, elementId1, elementId2);
        System.out.println(Connect.dataset.toString());
        Connect.dataset.setClassIndex(Connect.dataset.numAttributes() - 1);
        LinearRegression linearRegression = new LinearRegression();
        try {
            linearRegression.buildClassifier(Connect.dataset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double[] coef = linearRegression.coefficients();
        double myLinearRegression = (coef[0] * 300 + coef[2]);
        Map<Character, Double> map = new HashMap<>();
        map.put('k', coef[0]);
        map.put('c', coef[2]);
        String res = JSON.toJSONString(map);
        System.out.println(res);
        return res;
    }

    @Override
    public Double Average(String elementId, Integer dataId) {
        QueryWrapper<DataCollect> queryWrapper = new QueryWrapper<>();
        MyBatisPlusConfig.number = dataId;
        queryWrapper.select("output_value");
        queryWrapper.eq("member_id", elementId);
        List<DataCollect> list = dataCollectMapper.selectList(queryWrapper);
        double sum = 0;
        double max = Double.parseDouble(list.get(0).getOutputValue());
        double min = Double.parseDouble(list.get(0).getOutputValue());
        for (int i = 0; i < list.size(); i++){
            double temp = Double.parseDouble(list.get(i).getOutputValue());
            sum += temp;//数组总和
            if (temp > max){
                max = temp;
            }else if (temp < min){
                min = temp;
            }
        }
        return sum / list.size();
    }

    @Override
    public Double variance(String elementId, Integer dataId) {
        QueryWrapper<DataCollect> queryWrapper = new QueryWrapper<>();
        MyBatisPlusConfig.number = dataId;
        queryWrapper.select("output_value");
        queryWrapper.eq("member_id", elementId);
        queryWrapper.select("output_value");
        List<DataCollect> list = dataCollectMapper.selectList(queryWrapper);
        double total = 0;
        for (int i = 0; i < list.size(); i++){
            total += Math.pow(Double.parseDouble(list.get(i).getOutputValue()) - Average(elementId, dataId), 2);
        }
        return total;
    }

    @Override
    public Double StandardDeviation(String elementId, Integer dataId) {
        return Math.sqrt(variance(elementId, dataId));
    }
}
