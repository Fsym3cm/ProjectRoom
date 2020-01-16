package test;

import mapper.AreaMapper;
import mapper.CityMapper;
import mapper.ProvinceMapper;
import model.Area;
import model.City;
import model.Province;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class Demo03 {

    //加载spring的配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    //获取dao的bean
    ProvinceMapper provinceMapper = (ProvinceMapper) context.getBean("provinceMapper");
    CityMapper cityMapper = (CityMapper) context.getBean("cityMapper");
    AreaMapper areaMapper = (AreaMapper) context.getBean("areaMapper");
    @Test
    public void test01() {
//        查看该配置bean的所有bean
//        String[] str=context.getBeanDefinitionNames();
//        for (String string : str) {
//            System.out.println("..."+string);
//        }
//        System.out.println("----"+topicMapper);
        //调用dao的方法
        List<Province> provinces = provinceMapper.showAllProvince();
        for (Province province:provinces) {
            System.out.println(province);
        }
    }

    @Test
    public void test02(){
        Integer code = provinceMapper.queryCode("河北省");
        System.out.println(code);
    }

    @Test
    public void test03() {
        //调用dao的方法
        List<City> cities = cityMapper.queryCity(130000);
        for (City city:cities) {
            System.out.println(city);
        }
    }

    @Test
    public void test04(){
        Integer code = cityMapper.queryCode("北京市");
        System.out.println(code);
    }


    @Test
    public void test05() {
        //调用dao的方法
        List<Area> areas = areaMapper.queryArea(110100);
        for (Area area:areas) {
            System.out.println(area);
        }
    }


}