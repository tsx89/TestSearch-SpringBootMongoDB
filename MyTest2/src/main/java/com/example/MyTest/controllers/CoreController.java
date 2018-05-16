package com.example.MyTest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.MyTest.model.Entity;

import com.example.MyTest.repository.InitialSearchRepository;



@Controller
public class CoreController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    InitialSearchRepository initialSearchRepository;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("entityList", null);
        return "home";
    }


    @RequestMapping(value = "/search")
    public String search(Model model, @RequestParam String searchNo, @RequestParam String searchName, @RequestParam String searchCity, @RequestParam String searchPostCode) {

        //log.info("searchCity: " +  searchCity);
        log.info("searchPostCode: " + searchPostCode);


        model.addAttribute("entityList", initialSearchRepository.searchEntity(searchNo, searchName, searchCity, searchPostCode));
        model.addAttribute("searchNo", searchNo);
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchCity", searchCity);
        model.addAttribute("searchPostCode", searchPostCode);
        return "home";
    }
}