package com.chengzimm.dataAnalysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengzimm.dataAnalysis.model.SimuScheme;

import java.util.List;

public interface SimuSchemeService extends IService<SimuScheme> {
    public List<String> getName(List<String> list);
}
