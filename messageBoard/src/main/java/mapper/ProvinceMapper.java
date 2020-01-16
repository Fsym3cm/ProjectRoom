package mapper;

import model.Province;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface ProvinceMapper {

    @Select("SELECT * FROM `province`；")
    @ResultType(Province.class)
    //查询所有用户信息
    public List<Province> showAllProvince();

    @Select("SELECT code FROM `province` WHERE name = #{name};")
    @ResultType(Province.class)
    //查询所有用户信息
    public Integer queryCode(String name);
}
