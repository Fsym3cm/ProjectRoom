package service;

import model.User;
import java.util.List;

public interface UserService {
    //查询所有用户信息
    public List<User> showAllUser();
    //通过id查询用户信息
    public User queryUserId(int userId);
    //更新用户信息
    public void updateUser(User user);
    //插入用户信息
    public void insertUser(User user);
    //通过id删除用户
    public void deleteById(int userId);
    //更改用户密码
    public void updatePassword(User user);
    //用户登录
    public Integer loginUser(String userName,String userPassword);
}
