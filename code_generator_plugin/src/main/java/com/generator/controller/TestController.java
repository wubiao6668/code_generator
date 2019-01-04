package com.generator.controller;

import com.generator.annotation.Param;
import com.generator.annotation.RequestMapping;
import com.generator.annotation.RestController;
import com.generator.model.TestModel;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public List<Integer> list() {
        return Lists.newArrayList(1, 2, 3, 4, 4, 5);
    }

    @RequestMapping(value = "/test1")
    public List<Integer> list(TestModel testModel) {
        return Lists.newArrayList(1, 2, 3, 4, 4, 5);
    }

    @RequestMapping(value = "/test2")
    public List<Integer> list(String name) {
        return Lists.newArrayList(1, 2, 3, 4, 4, 5);
    }

    @RequestMapping(value = "/test3")
    public List<Integer> list(@Param("testModel") TestModel testModel, @Param("name") String name, Integer age, int sex) {
        return Lists.newArrayList(1, 2, 3, 4, 4, 5);
    }

    @RequestMapping(value = "/vdata")
    public List<Map> vdata() {
        List<Map> list = Lists.newArrayList();
        Map<String, String> map = null;
        for (int i = 0; i < 10; i++) {
            map = Maps.newHashMap();
            map.put("code", "code" + i);
            map.put("name", "name" + i);
            map.put("itemid", "itemid" + i);
            list.add(map);
        }
        return list;
    }


}
