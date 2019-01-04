package com.generator.model;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author wubiao
 */
@Data
public class MvcInvokeMethod {
    public Object bean;
    public Method method;



}
