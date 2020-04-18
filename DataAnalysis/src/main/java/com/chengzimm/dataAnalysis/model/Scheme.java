package com.chengzimm.dataAnalysis.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Scheme {

    private Integer schemeId;
    private String schemeName;
    private ArrayList memberList;
}
