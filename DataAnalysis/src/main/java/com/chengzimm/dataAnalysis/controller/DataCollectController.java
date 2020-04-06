package com.chengzimm.dataAnalysis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengzimm.dataAnalysis.model.DataCollect;
import com.chengzimm.dataAnalysis.service.DataCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("DataCollect")
public class DataCollectController {

    @Autowired
    private DataCollectService dataCollectService;

    @RequestMapping("show")
    public List<DataCollect> show(){
        return dataCollectService.list();
    }

    @RequestMapping("save")
    public Boolean save(@RequestBody DataCollect dataCollect){
        DataCollect data = new DataCollect();
        data.setFederationId(dataCollect.getFederationId());
        data.setMemberId(dataCollect.getMemberId());
        data.setOutputValue(dataCollect.getOutputValue());
        data.setStep(dataCollect.getStep());
        data.setTime(dataCollect.getTime());
        return dataCollectService.save(data);
    }

    @RequestMapping("update")
    public Boolean update(@RequestBody DataCollect dataCollect){
        DataCollect data = new DataCollect();
        data.setFederationId(dataCollect.getFederationId());
        data.setStep(dataCollect.getStep());
        return dataCollectService.updateById(data);
    }

    @RequestMapping("remove")
    public Boolean remove(@RequestBody DataCollect dataCollect){
        return dataCollectService.removeById(7);
    }

    @RequestMapping("selectValue")
    public List<DataCollect> selectValue(String federationId, String memberId, Double time){
        QueryWrapper<DataCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("output_value", "step");
        queryWrapper.eq("federation_id", "1").eq("member_id", "1").eq("time", 1.0);
        return dataCollectService.list(queryWrapper);
    }
}
