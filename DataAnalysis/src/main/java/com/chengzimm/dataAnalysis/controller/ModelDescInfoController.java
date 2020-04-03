package com.chengzimm.dataAnalysis.controller;

import com.chengzimm.dataAnalysis.model.ModelDescInfo;
import com.chengzimm.dataAnalysis.service.ModelDescInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ModelDescInfo")
public class ModelDescInfoController {

    @Autowired
    private ModelDescInfoService modelDescInfoService;

    @RequestMapping("hello")
    public String hello(){
        return "hello world!";
    }

    @RequestMapping("show")
    public List<ModelDescInfo> show(){
        return modelDescInfoService.list();
    }

    @RequestMapping("save")
    public Boolean save(@RequestBody ModelDescInfo modelDescInfo){
        ModelDescInfo info = new ModelDescInfo();
        info.setModelVersion(modelDescInfo.getModelVersion());
        info.setModelName(modelDescInfo.getModelName());
        info.setPublishTime(modelDescInfo.getPublishTime());
        info.setSponsor(modelDescInfo.getSponsor());
        info.setProprietorUser(modelDescInfo.getProprietorUser());
        info.setUpdateNum(modelDescInfo.getUpdateNum());
        return modelDescInfoService.save(info);
    }

    @RequestMapping("update")
    public Boolean update(@RequestBody ModelDescInfo modelDescInfo){
        ModelDescInfo info = new ModelDescInfo();
        info.setKeywords(modelDescInfo.getKeywords());
        return modelDescInfoService.updateById(modelDescInfo);
    }

    @RequestMapping("remove")
    public Boolean remove(@RequestBody ModelDescInfo modelDescInfo){
        return modelDescInfoService.removeById(7);
    }

}
