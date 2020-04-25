package com.chengzimm.dataAnalysis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengzimm.dataAnalysis.model.bean.*;
import com.chengzimm.dataAnalysis.model.DataCollect;
import com.chengzimm.dataAnalysis.service.DataCollectService;
import com.chengzimm.dataAnalysis.service.SimuSchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("DataCollect")
public class DataCollectController {

    @Autowired
    private DataCollectService dataCollectService;

    @Autowired
    private SimuSchemeService simuSchemeService;

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

    @RequestMapping("tree")
    public List<Scheme1> tree(){
        List<Integer> schemeIds = dataCollectService.showSchemeId();
        List<String> schemes = simuSchemeService.getName(schemeIds);
        List<List<String>> memberIds = dataCollectService.showMemberId();
        List<List<String>> attrs = dataCollectService.showAttr();


        ArrayList<Scheme1> SchemeList = new ArrayList<>();
        for (int i = 0; i < schemes.size(); i ++){
//            System.out.println("第" + i + "次运行：");
            ArrayList<Member1> memberList = new ArrayList<>();

            Scheme1 scheme = new Scheme1();
            scheme.setSchemeId(schemeIds.get(i));
            scheme.setSchemeName(schemes.get(i));
            scheme.setMemberList(memberList);
            SchemeList.add(scheme);

            for (int m = 0; m < memberIds.get(i).size(); m++){
                ArrayList<Data1> dataList = new ArrayList<>();

                Member1 member = new Member1();
                member.setMemberId(memberIds.get(i).get(m));
                member.setAttrList(dataList);
                memberList.add(member);

                if (m == 0){
                    for (int n = 0; n < attrs.get(i).size() - 2; n += 2){
                        Data1 data = new Data1();
                        data.setOutputValue(attrs.get(i).get(n));
                        data.setStep(Integer.parseInt(attrs.get(i).get(n + 1)));
                        dataList.add(data);
                    }
                } else if (m == 1){
                    for (int n = 6; n < attrs.get(i).size(); n += 2){
                        Data1 data = new Data1();
                        data.setOutputValue(attrs.get(i).get(n));
                        data.setStep(Integer.parseInt(attrs.get(i).get(n + 1)));
                        dataList.add(data);
                    }
                }

                }
        }
        return SchemeList;
    }

    @RequestMapping("dateTree")
    public List<Scheme> dateTree(){
        List<Integer> schemeIds = dataCollectService.showSchemeId();
        List<String> schemes = simuSchemeService.getName(schemeIds);
        List<List<String>> memberIds = dataCollectService.showMemberId();
        List<List<String>> attrs = dataCollectService.showAttr();


        ArrayList<Scheme> SchemeList = new ArrayList<>();
        for (int i = 0; i < schemes.size(); i ++){
//            System.out.println("第" + i + "次运行：");
            ArrayList<Member> memberList = new ArrayList<>();

            Scheme scheme = new Scheme();
            scheme.setLabel("方案名称：" + schemes.get(i));
            scheme.setChildren(memberList);
            SchemeList.add(scheme);

            for (int m = 0; m < memberIds.get(i).size(); m++){
                ArrayList<Data> dataList = new ArrayList<>();

                Member member = new Member();
                member.setLabel("成员ID：" + memberIds.get(i).get(m));
                member.setChildren(dataList);
                memberList.add(member);

                Data data = new Data();
                data.setLabel("运行信息");
                dataList.add(data);
            }


        }
        return SchemeList;
    }
   /* @RequestMapping("tree")
    public List<Scheme> tree(){
        List<Integer> schemeIds = dataCollectService.showSchemeId();
        List<String> schemes = simuSchemeService.getName(schemeIds);
        List<String> memberIds = dataCollectService.showMemberId();
        List<String> attrs = dataCollectService.showAttr();

        ArrayList<Scheme> SchemeList = new ArrayList<>();
        for (int i = 0, j = 0; i < schemes.size(); i ++, j += 2){
            ArrayList<Member> memberList = new ArrayList<>();
            ArrayList<Data> dataList = new ArrayList<>();

            Scheme scheme = new Scheme();
            Member member = new Member();
            Data data = new Data();

            scheme.setSchemeId(schemeIds.get(i));
            scheme.setSchemeName(schemes.get(i));
            scheme.setMemberList(memberList);
            SchemeList.add(scheme);

            member.setMemberId(memberIds.get(i));
            member.setAttrList(dataList);
            memberList.add(member);

            data.setOutputValue(attrs.get(j));
            data.setStep(Integer.parseInt(attrs.get(j + 1)));
            dataList.add(data);
        }

        return SchemeList;
    }*/


}
