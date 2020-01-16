package service;

import model.City;

import java.util.List;

public interface CityService {

    public List<City> queryCity(Integer provinceCode);

    public Integer queryCode(String name);
}
