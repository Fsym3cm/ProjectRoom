package controller;

import model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AreaService;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("area")
public class AreaControllerService {

    @Autowired
    private AreaService areaService;

    @RequestMapping("queryArea")
    @ResponseBody
    public List<Area> queryArea(Integer cityCode){
        List<Area> areas = areaService.queryArea(cityCode);
        return areas;
    }
}
