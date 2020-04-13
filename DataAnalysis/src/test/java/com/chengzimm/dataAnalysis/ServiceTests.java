package com.chengzimm.dataAnalysis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chengzimm.dataAnalysis.dao.DataDao;
import com.chengzimm.dataAnalysis.model.DataCollect;
import com.chengzimm.dataAnalysis.model.ModelDescInfo;
import com.chengzimm.dataAnalysis.service.DataCollectService;
import com.chengzimm.dataAnalysis.service.ModelDescInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.SimpleLinearRegression;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import java.text.SimpleDateFormat;
import java.util.List;


@SpringBootTest
public class ServiceTests {

    @Autowired
    private ModelDescInfoService modelDescInfoService;

    @Autowired
    private DataCollectService dataCollectService;

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

    @Test
    public void updateById(){
        ModelDescInfo modelDescInfo = new ModelDescInfo();
        modelDescInfo.setMUid(3);
        modelDescInfo.setSponsor("chengzimm");
        System.out.println(modelDescInfoService.updateById(modelDescInfo));
    }

    @Test
    public void list1(){
        QueryWrapper<ModelDescInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("m_uid", "sponsor");
        List<ModelDescInfo> modelDescInfoList = modelDescInfoService.list(queryWrapper);
        modelDescInfoList.forEach(System.out::println);
    }

    @Test
    public void getById(){
        System.out.println(modelDescInfoService.getById(1));
    }

    @Test
    public void showData(){
        QueryWrapper<DataCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("federation_id");
        List<DataCollect> list = dataCollectService.list(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void dataset(){
        Classifier cfs = null;
        try {
            InstanceQuery query = new InstanceQuery();
            query.setDatabaseURL("jdbc:mysql://localhost:3306/hla?DatabaseName=hla&characterEncoding=utf8&serverTimezone=UTC&autoReconnect=true&useSSL=false "); // 链接数据库
            query.setUsername("root");
            query.setPassword("123456");
            query.setQuery("select output_value, step from data_collect");
            Instances dataset = query.retrieveInstances();

            int len = dataset.numAttributes(); // 获取data1的属性个数
            dataset.setClassIndex(len - 1); //指定最后一个属性为data1的类标签
            dataset.attribute(0);

            SimpleLinearRegression slr = new SimpleLinearRegression();
            // 6.分类器初始化参数 设置入参
            slr.setDebug(false);//控制打印信息(改为true时程序控制台中没有变化)
            // 7.进行线性回归分析
            slr.buildClassifier(dataset);
            // 8.评估
            Evaluation eval = new Evaluation(dataset);
            eval.evaluateModel(slr, dataset);
            // 9.获取评价标准
            Double mean = eval.meanAbsoluteError();//平均绝对误差 或 剩余（残差）平方和 ：越小越好
            System.out.println(mean);
            // 10.获取模型公式
            String model = slr.toString();
            double sum = dataset.numInstances();//获取预测实例的总数

            // 11.获取待预测的数据（sql2中的待预测数据用？表示）
            DataDao dao = new DataDao();
            dao.query("1", "1", 2.0);
            Instances insTest = query.retrieveInstances();//获取待预测数据
            insTest.setClassIndex(dataset.numAttributes() - 1);//设置成相同的属性下标，默认都是最后一个属性

            // 方法二：调用分类器方法classifyInstance（Instance instance）循环输出每一个实例的预测值
            double sum2 = insTest.numInstances(); //获取预测实例的总数
            for(int i = 0; i < sum2; i++){
                System.out.println(insTest.instance(i).value(0) + " : " + slr.classifyInstance(insTest.instance(i)));
            }


            System.out.println(dataset.toString()); // 打印数据集
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
