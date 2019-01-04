package com.generator.service.impl;

import com.generator.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public boolean test() {
        return false;
    }
}
