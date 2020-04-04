package com.chengzimm.dataAnalysis.controller;

import com.chengzimm.dataAnalysis.model.ParaInfo;
import com.chengzimm.dataAnalysis.service.ParaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ParaInfo")
public class ParaInfoController {

    @Autowired
    private ParaInfoService paraInfoService;

    @RequestMapping("show")
    public List<ParaInfo> show(){
        return paraInfoService.list();
    }

    @RequestMapping("save")
    public Boolean save(@RequestBody ParaInfo paraInfo){
        ParaInfo info = new ParaInfo();
        info.setPId(paraInfo.getPId());
        info.setMUid(paraInfo.getMUid());
        info.setParaKey(paraInfo.getParaKey());
        info.setBasicType(paraInfo.getBasicType());
        info.setParaLength(paraInfo.getParaLength());
        info.setParaDesc(paraInfo.getParaDesc());
        return paraInfoService.save(info);
    }

    @RequestMapping("update")
    public Boolean update(@RequestBody ParaInfo paraInfo){
        ParaInfo info = new ParaInfo();
        info.setPId(paraInfo.getPId());
        info.setParaDesc(paraInfo.getParaDesc());
        return paraInfoService.updateById(info);
    }

    @RequestMapping("remove")
    public Boolean remove(@RequestBody ParaInfo paraInfo){
        return paraInfoService.removeById(7);
    }
}
