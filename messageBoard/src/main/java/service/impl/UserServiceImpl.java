package service.impl;

import mapper.UserMapper;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> showAllUser() {
        return userMapper.showAllUser();
    }

    @Override
    public User queryUserId(int userId) {
        return userMapper.queryUserId(userId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void deleteById(int userId) {
        userMapper.deleteById(userId);
    }

    @Override
    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }

    @Override
    public Integer loginUser(String username, String userpassword) {
      Integer id = userMapper.loginUser(username,userpassword);
      return id;
    }
}
