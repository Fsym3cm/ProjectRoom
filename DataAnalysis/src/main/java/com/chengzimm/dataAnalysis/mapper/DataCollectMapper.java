package com.chengzimm.dataAnalysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengzimm.dataAnalysis.model.DataCollect;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCollectMapper extends BaseMapper<DataCollect> {

    @Select("SELECT COUNT(*) FROM `data_collect`GROUP BY member_id;")
    public List<Integer> group();
}
