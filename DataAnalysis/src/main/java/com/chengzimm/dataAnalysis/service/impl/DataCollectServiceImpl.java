package com.chengzimm.dataAnalysis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengzimm.dataAnalysis.config.MyBatisPlusConfig;
import com.chengzimm.dataAnalysis.mapper.DataCollectMapper;
import com.chengzimm.dataAnalysis.model.DataCollect;
import com.chengzimm.dataAnalysis.service.DataCollectService;
import com.chengzimm.dataAnalysis.utills.Deduplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataCollectServiceImpl extends ServiceImpl<DataCollectMapper, DataCollect> implements DataCollectService {

    @Autowired
    private DataCollectMapper dataCollectMapper;

    QueryWrapper<DataCollect> queryWrapper = new QueryWrapper<>();

    /*@Override
        public List<Integer> showSchemeId() {
            queryWrapper.select("federation_id");
            List<DataCollect> list = dataCollectService.list(queryWrapper);
            List<Integer> temp = new ArrayList<>();
            for (DataCollect dataCollect : list){
                temp.add(Integer.valueOf(dataCollect.getFederationId().substring(0, 1)));
            }
            return temp;
        }*/
    @Override
    public List<Integer> showSchemeId() {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            MyBatisPlusConfig.number = i;
            queryWrapper.select("federation_id");
            List<DataCollect> list = dataCollectMapper.selectList(queryWrapper);
            temp.add(Integer.valueOf(list.get(0).getFederationId().substring(0, 1)));
        }
        return temp;
    }

    @Override
    public List<List<String>> showMemberId() {
        List<List<String>> target = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            List<String> temp = new ArrayList<>();
            MyBatisPlusConfig.number = i;
            queryWrapper.select("member_id");
            List<DataCollect> list = dataCollectMapper.selectList(queryWrapper);
            for (DataCollect dataCollect : list){
                temp.add(dataCollect.getMemberId());
            }
            List<String> list1 = Deduplication.deduplication(temp);
            target.add(list1);
        }
        return target;
    }

    @Override
    public List<List<String>> showAttr() {
        List<List<String>> target = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            List<String> temp = new ArrayList<>();
            MyBatisPlusConfig.number = i;
            queryWrapper.select("output_value", "step");
            queryWrapper.orderByAsc("member_id");
            List<DataCollect> list = dataCollectMapper.selectList(queryWrapper);
            for (DataCollect dataCollect : list){
                temp.add(dataCollect.getOutputValue());
                temp.add(dataCollect.getStep().toString());
            }
            target.add(temp);
        }
        return target;
    }

    @Override
    public  List<List<String>> showFederationId() {
        List<List<String>> target = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            List<String> temp = new ArrayList<>();
            MyBatisPlusConfig.number = i;
            queryWrapper.select("federation_id");
            queryWrapper.orderByAsc("member_id");
            List<DataCollect> list = dataCollectMapper.selectList(queryWrapper);
            for (DataCollect dataCollect : list){
                temp.add(dataCollect.getFederationId());
            }
            target.add(temp);
        }
        return target;
    }

    @Override
    public List<List<Integer>> group() {
        List<List<Integer>> target = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            MyBatisPlusConfig.number = i;
            List<Integer> list = dataCollectMapper.group();
            List<Integer> temp = new ArrayList<>();
            int sum = list.get(0);
            for (int m = 0; m < list.size(); m++){
                if (m == 0){
                    temp.add(list.get(0));
                }else {
                    sum += list.get(m);
                    temp.add(sum);
                }
            }
            target.add(temp);
        }
        return target;
    }
}
