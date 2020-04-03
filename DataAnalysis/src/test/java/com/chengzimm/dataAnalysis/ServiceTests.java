package com.chengzimm.dataAnalysis;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chengzimm.dataAnalysis.mapper.ModelDescInfoMapper;
import com.chengzimm.dataAnalysis.model.ModelDescInfo;
import com.chengzimm.dataAnalysis.service.ModelDescInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
public class ServiceTests {

    @Autowired
    private ModelDescInfoService modelDescInfoService;

    @Test
    public void list(){
        List<ModelDescInfo> modelDescInfoList = modelDescInfoService.list();
        modelDescInfoList.forEach(System.out::println);
    }

    @Test
    public void save(){
        java.util.Date date = new java.util.Date();   //获取系统时间
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //这是24时
        //  java.sql.Date time=new java.sql.Date(date.getTime());  //只保留年月日信息 2019-11-16
        String Date = temp.format(date);
        ModelDescInfo modelDescInfo = new ModelDescInfo();
        modelDescInfo.setModelVersion("2");
        modelDescInfo.setModelName("dangerous");
        modelDescInfo.setPublishTime(Date);
        modelDescInfo.setSponsor("fsym");
        modelDescInfo.setProprietorUser(2);
        modelDescInfo.setUpdateNum(1);

        System.out.println(modelDescInfoService.save(modelDescInfo));
    }

    @Test
    public void update(){
        ModelDescInfo modelDescInfo = new ModelDescInfo();
        modelDescInfo.setEmail("2046749835@qq.com");
        UpdateWrapper<ModelDescInfo> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("m_uid", "2");
        System.out.println(modelDescInfoService.update(modelDescInfo, userUpdateWrapper));
    }

    @Test
    public void remove(){
        System.out.println(modelDescInfoService.removeById(7));
    }

}
