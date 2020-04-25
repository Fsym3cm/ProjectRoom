package com.chengzimm.dataAnalysis.model.bean;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Member {

    private String label; //memberId
//    private String memberName;
    private ArrayList children;/*attrList*/

}
