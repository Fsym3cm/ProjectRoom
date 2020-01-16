package service;

import model.Province;

import java.util.List;

public interface ProvinceService {

    //查询所有用户信息
    public List<Province> showAllProvince();

    public Integer queryCode(String name);
}
