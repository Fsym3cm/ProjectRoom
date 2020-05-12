package com.chengzimm.dataAnalysis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengzimm.dataAnalysis.config.MyBatisPlusConfig;
import com.chengzimm.dataAnalysis.model.DataCollect;
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

    @RequestMapping("linearRegression/{elementId1}/{elementId2}/{dataId}")
    public List<List<Double>> linearRegression(@PathVariable("elementId1") String elementId1, @PathVariable("elementId2") String elementId2, @PathVariable("dataId") Integer dataId) {
        String str = null;
        List<List<Double>>  target = new ArrayList<>();
        MyBatisPlusConfig.number = dataId;
        QueryWrapper<DataCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", Integer.parseInt(elementId1));
        queryWrapper.orderByAsc("step");

        List<DataCollect> list1 = dataCollectService.list(queryWrapper);
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", Integer.parseInt(elementId2));
        queryWrapper.orderByAsc("step");
        List<DataCollect> list2 = dataCollectService.list(queryWrapper);

        int min = list1.size() < list2.size() ? list1.size() : list2.size();
        int max = list1.size() > list2.size() ? list1.size() : list2.size();
        for (int i = 0; i < min ; i++){
            List<Double> arr = new ArrayList<>();
            arr.add(Double.parseDouble(list1.get(i).getOutputValue()));
            arr.add(Double.parseDouble(list2.get(i).getOutputValue()));
            target.add(arr);
        }
       /* if (list1.size() > list2.size()){
            for (int i = 0; i < max - min; i++){
                List<Double> arr = new ArrayList<>();
                arr.add(Double.parseDouble(list1.get(i).getOutputValue()));
                target.add(arr);
            }
        } else {
            for (int i = 0; i < max - min; i++){
                List<Double> arr = new ArrayList<>();
                arr.add(Double.parseDouble(list2.get(i).getOutputValue()));
                target.add(arr);
            }
        }*/

      /*  dataId = dataId - 1;
        if (dataId != 0){
            str = "_" + dataId.toString();
            return dataAnalysisService.linearRegression(elementId1, elementId2, str);
        } else {
            str = "";
            return dataAnalysisService.linearRegression(elementId1, elementId2, str);
        }*/
        return target;
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
