package mapper;

import model.City;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CityMapper {

    @Select("SELECT * FROM `city` WHERE provincecode = #{provinceCode};")
    @ResultType(City.class)
    public List<City> queryCity(Integer provinceCode);

    @Select("SELECT code FROM `city` WHERE name = #{name};")
    @ResultType(City.class)
    public Integer queryCode(String name);
}
