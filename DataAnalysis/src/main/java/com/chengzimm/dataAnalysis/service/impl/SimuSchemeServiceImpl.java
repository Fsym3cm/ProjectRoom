package com.chengzimm.dataAnalysis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengzimm.dataAnalysis.mapper.SimuSchemeMapper;
import com.chengzimm.dataAnalysis.model.SimuScheme;
import com.chengzimm.dataAnalysis.service.SimuSchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimuSchemeServiceImpl extends ServiceImpl<SimuSchemeMapper, SimuScheme> implements SimuSchemeService {

    @Autowired
    private SimuSchemeService simuSchemeService;

    @Override
    public List<String> getName(List<Integer> list) {
        List<String> temp = new ArrayList<>();
        System.out.println(list);
        for (int i = 0; i < list.size(); i++){
            temp.add(simuSchemeService.getById(list.get(i)).getSchemeName());
        }
        return temp;
    }

}
