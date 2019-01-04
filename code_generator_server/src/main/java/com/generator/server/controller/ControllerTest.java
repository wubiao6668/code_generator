package com.generator.server.controller;

import com.generator.server.domain.FieldDomain;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wubiao
 */
@RestController
public class ControllerTest {

    @RequestMapping("test")
    public List<Integer> test(@RequestBody FieldDomain fieldDomain) {
        return Lists.newArrayList(1, 2, 3, 4, 5, 5, 6);
    }

}
