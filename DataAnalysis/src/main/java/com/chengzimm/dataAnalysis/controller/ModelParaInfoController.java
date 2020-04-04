package com.chengzimm.dataAnalysis.controller;

import com.chengzimm.dataAnalysis.model.ModelParaInfo;
import com.chengzimm.dataAnalysis.service.ModelParaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ModelParaInfo")
public class ModelParaInfoController {

    @Autowired
    private ModelParaInfoService modelParaInfoService;

    @RequestMapping("show")
    public List<ModelParaInfo> show(){
        return modelParaInfoService.list();
    }

    @RequestMapping("save")
    public Boolean save(@RequestBody ModelParaInfo modelParaInfo){
        ModelParaInfo info = new ModelParaInfo();
        info.setMUid(modelParaInfo.getMUid());
        info.setInputParas(modelParaInfo.getInputParas());
        info.setOutputParas(modelParaInfo.getOutputParas());
        info.setInitParas(modelParaInfo.getInitParas());
        return modelParaInfoService.save(info);
    }

    @RequestMapping("update")
    public Boolean update(@RequestBody ModelParaInfo modelParaInfo){
        ModelParaInfo info = new ModelParaInfo();
        info.setMUid(modelParaInfo.getMUid());
        info.setInputParas(modelParaInfo.getInputParas());
        return modelParaInfoService.updateById(info);
    }

    @RequestMapping("remove")
    public Boolean remove(@RequestBody ModelParaInfo modelParaInfo){
        return modelParaInfoService.removeById(7);
    }
}
