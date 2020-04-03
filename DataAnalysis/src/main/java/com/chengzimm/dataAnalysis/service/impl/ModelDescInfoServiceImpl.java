package com.chengzimm.dataAnalysis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengzimm.dataAnalysis.mapper.ModelDescInfoMapper;
import com.chengzimm.dataAnalysis.model.ModelDescInfo;
import com.chengzimm.dataAnalysis.service.ModelDescInfoService;
import org.springframework.stereotype.Service;

@Service
public class ModelDescInfoServiceImpl extends ServiceImpl<ModelDescInfoMapper, ModelDescInfo> implements ModelDescInfoService {

    /*@Autowired
    private ModelDescInfoMapper modelDescInfoMapper;

    @Override
    public List<ModelDescInfo> selectList() {
        return modelDescInfoMapper.selectList(null);
    }

    @Override
    public Integer insert(ModelDescInfo modelDescInfo) {
        return modelDescInfoMapper.insert(modelDescInfo);

    }

    @Override
    public Integer updateWrapper(ModelDescInfo modelDescInfo, UpdateWrapper<ModelDescInfo> userUpdateWrapper) {
        return modelDescInfoMapper.update(modelDescInfo, userUpdateWrapper);
    }

    @Override
    public Integer delete(QueryWrapper<ModelDescInfo> queryWrapper) {
        return modelDescInfoMapper.delete(queryWrapper);
    }*/
}
