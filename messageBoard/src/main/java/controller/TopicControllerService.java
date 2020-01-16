package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import service.TopicService;

@Controller
@CrossOrigin //整个Controller都支持跨域访问，在类上面加上注解@CrossOrigin
@RequestMapping("topic")
public class TopicControllerService {

    @Autowired
    private TopicService topicService;




}
