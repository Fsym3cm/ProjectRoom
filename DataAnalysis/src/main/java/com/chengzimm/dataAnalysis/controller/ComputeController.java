package com.chengzimm.dataAnalysis.controller;

import com.chengzimm.dataAnalysis.model.bean.Member2;
import com.chengzimm.dataAnalysis.model.bean.Method;
import com.chengzimm.dataAnalysis.service.DataAnalysisService;
import com.chengzimm.dataAnalysis.service.DataCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("Compute")
public class ComputeController {

    @Autowired
    private DataAnalysisService dataAnalysisService;

    @Autowired
    private DataCollectService dataCollectService;

    @RequestMapping("show")
    public String show() {
        return dataAnalysisService.outputValue("", "1");
    }

    @RequestMapping("methodsTree/{dataId}")
    public List<Method> methodsTree(@PathVariable("dataId") Integer dataId) {
        List<Method> methodList = new ArrayList<>();
        List<String> methods = new ArrayList<>();
        methods.add("分类分析");
        methods.add("仿真时域");
        methods.add("线性回归");
        methods.add("关联分析");
        List<String> memberList = dataCollectService.showMemberId(dataId);
        ArrayList<Member2> members = new ArrayList<>();
        for (int j = 0; j < memberList.size(); j++){
            Member2 member2 = new Member2();
            member2.setValue(String.valueOf(j));
            member2.setLabel("成员" + memberList.get(j));
            members.add(member2);
        }
        for (int i = 0; i < 4; i++){
            Method method = new Method();
            method.setValue(String.valueOf(i));
            method.setLabel(methods.get(i));
            method.setChildren(members);
            methodList.add(method);
        }

        return methodList;
    }
}
