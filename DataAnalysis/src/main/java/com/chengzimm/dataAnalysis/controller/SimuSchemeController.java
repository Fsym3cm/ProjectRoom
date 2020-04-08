package com.chengzimm.dataAnalysis.controller;

import com.chengzimm.dataAnalysis.model.SimuScheme;
import com.chengzimm.dataAnalysis.service.SimuSchemeService;
import com.chengzimm.dataAnalysis.utills.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
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

    @RequestMapping("save")
    public Boolean save(@RequestBody SimuScheme simuScheme){
        SimuScheme scheme = new SimuScheme();
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

    @RequestMapping("update")
    public Boolean update(@RequestBody SimuScheme simuScheme){
        SimuScheme scheme = new SimuScheme();
        scheme.setSchemeId(simuScheme.getSchemeId());
        scheme.setSchemeName(simuScheme.getSchemeName());
        return simuSchemeService.updateById(scheme);
    }

    @RequestMapping("remove")
    public Boolean remove(@RequestBody SimuScheme simuScheme){
        return simuSchemeService.removeById(7);
    }
}
