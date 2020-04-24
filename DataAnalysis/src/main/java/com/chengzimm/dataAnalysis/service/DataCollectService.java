package com.chengzimm.dataAnalysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengzimm.dataAnalysis.model.DataCollect;
import java.util.List;

public interface DataCollectService extends IService<DataCollect> {

    List<Integer> showSchemeId();
    List<List<String>> showMemberId();
    List<List<String>> showAttr();
}
