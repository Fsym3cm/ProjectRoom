package com.chengzimm.dataAnalysis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SimuScheme {

    @TableId(value = "scheme_id", type = IdType.AUTO)
    private Integer schemeId;
    private String schemeName;
    private String beginTime;
    private String endTime;
    private String generateDate;
    private String schemeAuthor;
    private String schemeDesc;
    private byte[] schemeFile;
    private byte[] isPublish;
}
