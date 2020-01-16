package service.impl;

import mapper.TopicMapper;
import model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.TopicService;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<Topic> showAllTopic() {
        return topicMapper.showAllTopic();
    }

    @Override
    public Topic queryUserId(int topicId) {
        return topicMapper.queryUserId(topicId);
    }

    @Override
    public void insertTopic(Topic topic) {
        topicMapper.insertTopic(topic);
    }

    @Override
    public void deleteTopic(int topicId) {
        topicMapper.deleteTopic(topicId);
    }
}
