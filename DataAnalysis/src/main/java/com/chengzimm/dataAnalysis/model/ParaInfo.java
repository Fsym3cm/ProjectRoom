package com.chengzimm.dataAnalysis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ParaInfo {

    @TableId(value = "m_uid", type = IdType.AUTO)
    private Integer MUid;
    private Integer PId;
    private String paraKey;
    private String basicType;
    private Integer paraLength;
    private String paraDesc;
}
