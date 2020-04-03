package com.chengzimm.dataAnalysis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class DataCollect {
    @TableId(value = "Federation_id", type = IdType.AUTO)
    private Integer federationId;
    private Integer memberId;
    private String outputValue;
    private Integer step;
    private Double time;
}
