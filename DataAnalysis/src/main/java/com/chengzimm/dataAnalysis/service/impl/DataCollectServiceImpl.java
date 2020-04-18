package com.chengzimm.dataAnalysis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengzimm.dataAnalysis.mapper.DataCollectMapper;
import com.chengzimm.dataAnalysis.model.DataCollect;
import com.chengzimm.dataAnalysis.service.DataCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataCollectServiceImpl extends ServiceImpl<DataCollectMapper, DataCollect> implements DataCollectService {

    @Autowired
    private DataCollectService dataCollectService;

    QueryWrapper<DataCollect> queryWrapper = new QueryWrapper<>();

    @Override
    public List<Integer> showSchemeId() {
        queryWrapper.select("federation_id");
        List<DataCollect> list = dataCollectService.list(queryWrapper);
        List<Integer> temp = new ArrayList<>();
        for (DataCollect dataCollect : list){
            temp.add(Integer.valueOf(dataCollect.getFederationId().substring(0, 1)));
        }
        return temp;
    }

    @Override
    public List<String> showMemberId() {
        queryWrapper.select("member_id");
        List<DataCollect> list = dataCollectService.list(queryWrapper);
        List<String> temp = new ArrayList<>();
        for (DataCollect dataCollect : list){
            temp.add(dataCollect.getMemberId());
        }
        return temp;
    }

    @Override
    public List<String> showAttr() {
        queryWrapper.select("output_value", "step");
        List<DataCollect> list = dataCollectService.list(queryWrapper);
        List<String> temp = new ArrayList<>();
        for (DataCollect dataCollect : list){
            temp.add(dataCollect.getOutputValue());
            temp.add(dataCollect.getStep().toString());
        }
        return temp;
    }

}
