package com.chengzimm.dataAnalysis.service.impl;

import com.alibaba.fastjson.JSON;
import com.chengzimm.dataAnalysis.dao.DataDao;
import com.chengzimm.dataAnalysis.service.DataAnalysisService;
import com.chengzimm.dataAnalysis.utills.Connect;
import org.springframework.stereotype.Service;
import weka.classifiers.functions.LinearRegression;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {

    DataDao dataDao = new DataDao();

    @Override
    public String outputValue(String dataId, String elementId) {
        dataDao.queryByDataId("", "1", "2");
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
        System.out.println(myLinearRegression);
        return res;
    }
}
