package com.chengzimm.dataAnalysis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @RequestMapping("dataTree")
    public List<DataCollect> dataTree() {
        // 整体思路：
        // 1、取得所有数据、放入集合List1 （tbCategories）
        // 2、将List1所有数据都放入到map（treeMap）中：元素id为键，元素本身对象为值
        // 3、取得顶层节点放入集合List2中（resultList）
        // 4、遍历List1中的所有数据，通过数据的parentId为键在map中取值
        //      1）如果能取到，则说明该元素有父节点
        //           1、判断该父节点下的childList中是否有已经子节点
        //              1、若无：则创建一个集合，将子节点放入
        //              2、若有：则直接将子节点放入即可
        // 5、把放好的数据放回到map中
        // 6、返回List2（resultList）

        // 注意：整个过程将所有数据取出放入list2（resultList）,返回的也是    //list2


        List<DataCollect> tbCategories = dataCollectService.list();

        List<DataCollect> resultList = new ArrayList<>(); // 存贮顶层的数据

        Map<Object, Object> treeMap = new HashMap();
        Object itemTree;

        /*for (int i = 0; i < tbCategories.size() && !tbCategories.isEmpty(); i++) {
            itemTree = tbCategories.get(i);
            treeMap.put(tbCategories.get(i).getNodeId(), tbCategories.get(i));// 把所有的数据都放到map中
        }*/
        return resultList;
    }


    @RequestMapping("tree")
    public List<List<String>> tree(){
        List<String> shemes = simuSchemeService.getName(dataCollectService.showSchemeId());
        List<List<String>> target = new ArrayList<>();
        List<String> arrayList = new ArrayList<>();
        List<List<String>> temp = new ArrayList<>();
        for (String scheme : shemes){
            temp.add(Collections.singletonList(scheme));
        }
        return temp;
    }
}
