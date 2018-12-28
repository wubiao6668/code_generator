package com.generator.controller;

import com.generator.annotation.RequestMapping;
import com.generator.annotation.RestController;
import com.generator.model.TestModel;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public List<Integer> list(TestModel testModel) {
        return Lists.newArrayList(1, 2, 3, 4, 4, 5);
    }

    @RequestMapping(value = "/test2")
    public List<Integer> lists(TestModel testModel,String name,Integer age,int sex) {
        return Lists.newArrayList(1, 2, 3, 4, 4, 5);
    }


}
