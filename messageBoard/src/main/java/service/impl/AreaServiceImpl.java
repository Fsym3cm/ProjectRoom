package service.impl;

import mapper.AreaMapper;
import model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AreaService;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> queryArea(Integer cityCode) {
        return areaMapper.queryArea(cityCode);
    }
}
