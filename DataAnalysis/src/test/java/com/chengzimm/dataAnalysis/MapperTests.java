package com.chengzimm.dataAnalysis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chengzimm.dataAnalysis.mapper.ModelDescInfoMapper;
import com.chengzimm.dataAnalysis.model.ModelDescInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class MapperTests {

    @Autowired
    private ModelDescInfoMapper modelDescInfoMapper;

    @Test//查看所有信息
    public void testSelectList(){
        List<ModelDescInfo> ModelDescInfoList = modelDescInfoMapper.selectList(null);
        ModelDescInfoList.forEach(System.out::println);
    }

    @Test//增加信息
    public void testInsert(){
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
        modelDescInfoMapper.insert(modelDescInfo);
    }

    @Test//修改信息
    public void testUpdateWrapper(){
        ModelDescInfo modelDescInfo = new ModelDescInfo();
        modelDescInfo.setEmail("2224590714@qq.com");
        UpdateWrapper<ModelDescInfo> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("m_uid", "2");
        modelDescInfoMapper.update(modelDescInfo, userUpdateWrapper);
    }

    @Test//删除信息
    public void testDelete(){
        QueryWrapper<ModelDescInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("sponsor");
        int delete = modelDescInfoMapper.delete(queryWrapper);
        System.out.println("删除了" + delete + "行数据");
    }
}
