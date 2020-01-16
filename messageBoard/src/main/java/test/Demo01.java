package test;

import mapper.UserMapper;
import org.junit.Test;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class Demo01 {

    //加载spring的配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    //获取dao的bean
    UserMapper userMapper = (UserMapper) context.getBean("userMapper");

    @Test
    public void test01(){
        //调用dao的方法
        Integer id = userMapper.loginUser("cm","123");
            System.out.println(id);

    }

//    @Test
//    public void test02(){
//        User user = new User();
//        user.setUsername("小红");
//        user.setUserpassword("zxcvbn");
//        Integer id = userMapper.loginUser1(user);
//        System.out.println(id);
//    }

    @Test
    public void test03(){
        //调用dao的方法
        User user = userMapper.queryUserId(1);
        System.out.println(user.getNickname());

    }

    @Test
    public void test04(){
        //调用dao的方法
        User user = new User();
        user.setUserName("123333");
        user.setUserPassword("zxcv4bn");
        user.setUserType(2);
        user.setNickname("cmcm");
        userMapper.insertUser(user);
    }

    @Test
    public void test05(){
        //调用dao的方法
        User user = new User();
        user.setUserId(5);
        user.setUserPassword("zxcv");
        userMapper.updatePassword(user);
    }

    @Test
    public void test06(){
        //调用dao的方法
        User user = new User();
        user.setUserId(3);
        user.setName("小小明");
        user.setNickname("xxm");
        user.setTelephone((double) 123321);
        user.setEmail("zkk@qq.com");
        userMapper.updateUser(user);
    }
    @Test
    public void test07() {
        //调用dao的方法
        List<User> users = userMapper.showAllUser();
        for (User user:users) {
            System.out.println(user);
        }
    }

}
