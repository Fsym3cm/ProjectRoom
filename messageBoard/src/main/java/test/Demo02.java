package test;

import mapper.TopicMapper;
import model.Topic;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class Demo02 {

    //加载spring的配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    //获取dao的bean
    TopicMapper topicMapper = (TopicMapper) context.getBean("topicMapper");

    @Test
    public void test01() {
//        查看该配置bean的所有bean
//        String[] str=context.getBeanDefinitionNames();
//        for (String string : str) {
//            System.out.println("..."+string);
//        }
//        System.out.println("----"+topicMapper);
        //调用dao的方法
        List<Topic> topics = topicMapper.showAllTopic();
        for (Topic topic:topics) {
            System.out.println(topic);
        }
    }

    @Test
    public void test02(){
        //调用dao的方法
        Topic topic = topicMapper.queryUserId(1);
        System.out.println(topic);
    }

    @Test
    public void test03(){
        //调用dao的方法
        Topic top = new Topic();
        top.setTopicTitle("111");
        top.setTopicContent("111");
        top.setTopicTime("2019-12-15 16:01:14");
        top.setTopicType(1);
        topicMapper.insertTopic(top);
    }

    @Test
    public void test04(){
        //调用dao的方法
        topicMapper.deleteTopic(3);
    }
}