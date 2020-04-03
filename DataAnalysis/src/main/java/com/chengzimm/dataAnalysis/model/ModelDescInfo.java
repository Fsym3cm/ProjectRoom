package com.chengzimm.dataAnalysis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ModelDescInfo {

    @TableId(value = "m_uid", type = IdType.AUTO)
    private Integer MUid;
    private String modelVersion;
    private String modelName;
    private String publishTime;
    private String modifiedTime;
    private String sponsor;
    private String email;
    private Integer modifier;
    private Integer proprietorUser;
    private Integer proprietorGroup;
    private String keywords;
    private String modelIntro;
    private Integer updateNum;

}
