package com.chengzimm.dataAnalysis.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class DataCollect {

    @TableId(value = "federation_id")
    private String federationId;
    private String memberId;
    private String outputValue;
    private Integer step;
    private Double time;

    /*private ArrayList memberList;
    private ArrayList attributeList;*/
}
