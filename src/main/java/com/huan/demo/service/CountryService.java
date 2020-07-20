package com.huan.demo.service;

import com.huan.demo.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Iterable<Country> findAll();
    Optional<Country> findById(Long id);
}
