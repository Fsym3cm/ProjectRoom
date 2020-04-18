package com.chengzimm.dataAnalysis.controller;

import com.chengzimm.dataAnalysis.model.SimuScheme;
import com.chengzimm.dataAnalysis.service.SimuSchemeService;
import com.chengzimm.dataAnalysis.utills.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("SimuScheme")
public class SimuSchemeController {

    @Autowired
    private SimuSchemeService simuSchemeService;

    @RequestMapping("show")
    public List<SimuScheme> show(){
        return simuSchemeService.list();
    }

    @RequestMapping("saveUTC")
    public Boolean saveUTC(@RequestBody SimuScheme simuScheme){
        SimuScheme scheme = new SimuScheme();
        System.out.println(simuScheme.getBeginTime());
        scheme.setSchemeName(simuScheme.getSchemeName());
        scheme.setBeginTime(DateUtil.dealDateFormat(simuScheme.getBeginTime()));
        scheme.setEndTime(DateUtil.dealDateFormat(simuScheme.getEndTime()));
        scheme.setGenerateDate(DateUtil.dealDateFormat(simuScheme.getGenerateDate()));
        scheme.setSchemeAuthor(simuScheme.getSchemeAuthor());
        scheme.setSchemeDesc(simuScheme.getSchemeDesc());
        scheme.setSchemeFile(simuScheme.getSchemeFile());
        scheme.setIsPublish(simuScheme.getIsPublish());
        return simuSchemeService.save(scheme);
    }

    @RequestMapping("save")
    public Boolean save(@RequestBody SimuScheme simuScheme){
        SimuScheme scheme = new SimuScheme();
        scheme.setSchemeName(simuScheme.getSchemeName());
        scheme.setBeginTime(simuScheme.getBeginTime());
        scheme.setEndTime(simuScheme.getEndTime());
        scheme.setGenerateDate(simuScheme.getGenerateDate());
        scheme.setSchemeAuthor(simuScheme.getSchemeAuthor());
        scheme.setSchemeDesc(simuScheme.getSchemeDesc());
        scheme.setSchemeFile(simuScheme.getSchemeFile());
        scheme.setIsPublish(simuScheme.getIsPublish());
        return simuSchemeService.save(scheme);
    }

    @RequestMapping("update")
    public Boolean update(@RequestBody SimuScheme simuScheme){
        SimuScheme scheme = new SimuScheme();
        scheme.setSchemeId(simuScheme.getSchemeId());
        scheme.setSchemeName(simuScheme.getSchemeName());
        scheme.setBeginTime(simuScheme.getBeginTime());
        scheme.setEndTime(simuScheme.getEndTime());
        scheme.setGenerateDate(simuScheme.getGenerateDate());
        scheme.setSchemeAuthor(simuScheme.getSchemeAuthor());
        scheme.setSchemeDesc(simuScheme.getSchemeDesc());
        scheme.setSchemeFile(simuScheme.getSchemeFile());
        scheme.setIsPublish(simuScheme.getIsPublish());
        return simuSchemeService.updateById(scheme);
    }

    @RequestMapping("removeById/{schemeId}")
    public Boolean removeById(@PathVariable("schemeId") Integer schemeId){
        return simuSchemeService.removeById(schemeId);
    }

    @GetMapping("getById/{schemeId}")
    public SimuScheme getById(@PathVariable("schemeId") Integer schemeId){
        return simuSchemeService.getById(schemeId);
    }
}
