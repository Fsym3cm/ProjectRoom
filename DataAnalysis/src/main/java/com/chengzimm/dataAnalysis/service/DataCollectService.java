package com.chengzimm.dataAnalysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengzimm.dataAnalysis.model.DataCollect;

import java.util.List;

public interface DataCollectService extends IService<DataCollect> {

    public List<String> showSchemeId();
}
