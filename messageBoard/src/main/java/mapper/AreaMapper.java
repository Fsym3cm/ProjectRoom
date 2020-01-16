package mapper;

import model.Area;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface AreaMapper {

    @Select("SELECT * FROM `area` WHERE citycode = #{cityCode};")
    @ResultType(Area.class)
    public List<Area> queryArea(Integer cityCode);
}
