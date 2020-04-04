package com.chengzimm.dataAnalysis.controller;

import com.chengzimm.dataAnalysis.model.DataCollect;
import com.chengzimm.dataAnalysis.service.DataCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.functions.SMO;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

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

    @RequestMapping("Average")
    public Integer Average(){
        try {
            InstanceQuery query = new InstanceQuery();
            query.setUsername("root");
            query.setPassword("123456");
            query.setQuery("select * from data_collect");
            Instances dataset = query.retrieveInstances();
            System.out.println(dataset.toString()); // 打印数据集
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
