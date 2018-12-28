package com.generator.controller;

import com.generator.annotation.RequestMapping;
import com.generator.annotation.RestController;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public List<Integer> list() {
        return Lists.newArrayList(1, 2, 3, 4, 4, 5);
    }

    @RequestMapping(value = "/test2")
    public List<Integer> lists(String namg,Integer age) {
        return Lists.newArrayList(1, 2, 3, 4, 4, 5);
    }


}
