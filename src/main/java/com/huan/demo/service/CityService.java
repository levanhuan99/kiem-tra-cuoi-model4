package com.huan.demo.service;

import com.huan.demo.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> findAll();
    Optional<City> findById(Long id);
    void save(City city);
    void delete(City city);

}
