package controller;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ProvinceService;

import java.util.List;

@Controller
@CrossOrigin //整个Controller都支持跨域访问，在类上面加上注解@CrossOrigin
@RequestMapping("province")
public class ProvinceControllerService {

    @Autowired
    private ProvinceService provinceService;


    @RequestMapping("showAllProvince")
    @ResponseBody
    public List<Province> showAllProvince(){
        List<Province> provinces = provinceService.showAllProvince();
        return provinces;
    }

    @RequestMapping("queryCode")
    @ResponseBody
    public Integer queryCode(String name){
        Integer code = provinceService.queryCode(name);
        return code;
    }
}
