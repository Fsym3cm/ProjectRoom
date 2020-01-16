package controller;

import model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CityService;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("city")
public class CityControllerService {

    @Autowired
    private CityService cityService;

    @RequestMapping("queryCity")
    @ResponseBody
    public List<City> queryCity(Integer provinceCode){
        List<City> cities = cityService.queryCity(provinceCode);
        return cities;
    }

    @RequestMapping("queryCode")
    @ResponseBody
    public Integer queryCode(String name){
        Integer code = cityService.queryCode(name);
        return code;
    }
}
