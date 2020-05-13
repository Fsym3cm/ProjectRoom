package com.chengzimm.dataAnalysis.service;

public interface DataAnalysisService {

    String linearRegression(String elementId1, String elementId2, String dataId);
    Double Average(String elementId, Integer dataId);
    Double variance(String elementId, Integer dataId);
    Double StandardDeviation(String elementId, Integer dataId);
}
