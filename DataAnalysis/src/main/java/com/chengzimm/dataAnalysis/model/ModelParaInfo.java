package com.chengzimm.dataAnalysis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ModelParaInfo {

    @TableId(value = "m_uid", type = IdType.AUTO)
    private Integer MUid;
    private String inputParas;
    private String outputParas;
    private String initParas;

}
