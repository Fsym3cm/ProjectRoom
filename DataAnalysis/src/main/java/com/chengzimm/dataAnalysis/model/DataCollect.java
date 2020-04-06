package com.chengzimm.dataAnalysis.model;

import lombok.Data;

@Data
public class DataCollect {

    private String federationId;
    private String memberId;
    private String outputValue;
    private Integer step;
    private Double time;
}
