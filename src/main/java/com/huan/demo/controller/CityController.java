package com.huan.demo.controller;

import com.huan.demo.model.City;
import com.huan.demo.model.Country;
import com.huan.demo.service.CityService;
import com.huan.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;


    @Autowired
    private CountryService countryService;

    @ModelAttribute("countries")
    public List<Country> countries() {
        return (List<Country>) countryService.findAll();
    }

    @GetMapping
    public ModelAndView getAllCity() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("cities", cityService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = null;
        if (city.isPresent()) {
            modelAndView = new ModelAndView("/edit-form");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            modelAndView.addObject("error");
            return modelAndView;
        }

    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute City city) {
        cityService.save(city);
        return "redirect:/city";

    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id) {

        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = null;
        if (city.isPresent()) {
            modelAndView = new ModelAndView("/delete-form");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            modelAndView.addObject("error");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute City city) {
        cityService.delete(city);
        return "redirect:/city";
    }

    @GetMapping("/add")
    public ModelAndView addCity() {
        ModelAndView modelAndView = new ModelAndView("/add-form");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute City city) {
        cityService.save(city);
        return "redirect:/city";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView getCityDetail(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = null;
        if (city.isPresent()) {
            modelAndView = new ModelAndView("/detail");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            modelAndView.addObject("error");
            return modelAndView;
        }
    }
}
