package com.chengzimm.dataAnalysis.model.bean;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Scheme {

//    private Integer schemeId;
    private String label; //schemeName
    private ArrayList children;/*memberList*/
}
