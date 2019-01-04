package com.generator.context;

import com.generator.config.RootConfig;
import com.generator.model.MvcInvokeMethod;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wubiao
 */
public class Application {

    public static ConcurrentHashMap<String, MvcInvokeMethod> webMethodMap = new ConcurrentHashMap<>();
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);

}
