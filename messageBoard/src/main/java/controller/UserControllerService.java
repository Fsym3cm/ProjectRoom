package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

@Controller
@CrossOrigin //整个Controller都支持跨域访问，在类上面加上注解@CrossOrigin
@RequestMapping("user")
public class UserControllerService {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    //@responseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据。
    //注意：在使用此注解之后不会再走视图处理器，而是直接将数据写入到输入流中，他的效果等同于通过response对象输出指定格式的数据。
    public Integer login(String userName,String userPassword) {
        Integer id = userService.loginUser(userName,userPassword);
        return id;
    }

    @RequestMapping("register")
    @ResponseBody
    public void register(@RequestBody User user){
        User us = new User();
        us.setUserName(user.getUserName());
        us.setUserPassword(user.getUserPassword());
        us.setUserType(user.getUserType());
        us.setNickname(user.getNickname());
        userService.insertUser(us);
    }

    @RequestMapping("queryUserId")
    @ResponseBody
    public User queryUserId(int userId){
        User user = userService.queryUserId(userId);
        return user;
    }

    @RequestMapping("updateUser")
    @ResponseBody
    public void updateUser(@RequestBody User user){
        User us = new User();
        us.setUserId(user.getUserId());
        us.setName(user.getName());
        us.setNickname(user.getNickname());
        us.setTelephone(user.getTelephone());
        us.setEmail(user.getEmail());
        userService.updateUser(us);
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public void updatePassword(@RequestBody User user){
        User us = new User();
        us.setUserId(user.getUserId());
        us.setUserPassword(user.getUserPassword());
        userService.updatePassword(us);
    }
}

