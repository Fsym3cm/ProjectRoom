package com.chengzimm.dataAnalysis.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DataCollect {

    private String federationId;
    private String memberId;
    private String outputValue;
    private Integer step;
    private Double time;

    /*private ArrayList memberList;
    private ArrayList attributeList;*/
}
