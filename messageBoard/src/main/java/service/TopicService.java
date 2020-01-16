package service;

import model.Topic;

import java.util.List;

public interface TopicService {

    //查询所有话题信息
    public List<Topic> showAllTopic();
    //通过id查询话题信息
    public Topic queryUserId(int topicId);
    //插入话题信息
    public void insertTopic(Topic topic);
    //通过id删除发布话题
    public void deleteTopic(int topicId);
}
