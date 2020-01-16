package service.impl;

import mapper.ProvinceMapper;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProvinceService;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;


    @Override
    public List<Province> showAllProvince() {
        return provinceMapper.showAllProvince();
    }

    @Override
    public Integer queryCode(String name) {
        return provinceMapper.queryCode(name);
    }
}
