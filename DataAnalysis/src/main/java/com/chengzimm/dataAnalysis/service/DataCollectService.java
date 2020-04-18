package com.chengzimm.dataAnalysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengzimm.dataAnalysis.model.DataCollect;

import java.util.ArrayList;
import java.util.List;

public interface DataCollectService extends IService<DataCollect> {

    List<Integer> showSchemeId();
    List<String> showMemberId();
    List<String> showAttr();
}
