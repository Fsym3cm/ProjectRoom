package service.impl;

import mapper.CityMapper;
import model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CityService;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;


    @Override
    public List<City> queryCity(Integer provinceCode) {
        return cityMapper.queryCity(provinceCode);
    }

    @Override
    public Integer queryCode(String name) {
        return cityMapper.queryCode(name);
    }
}
