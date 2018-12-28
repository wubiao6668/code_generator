package com.generator.processor;

import com.generator.annotation.RequestMapping;
import com.generator.annotation.RestController;
import com.generator.model.MvcInvokeMethod;
import com.generator.util.UrlUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wubiao
 */
@Component
public class MvcBeanPostProcessor implements BeanPostProcessor {

    public static ConcurrentHashMap<String, MvcInvokeMethod> invokeMethodConcurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        //1、class
        //find restController
        RestController restController = AnnotationUtils.findAnnotation(beanClass, RestController.class);
        if (restController == null) {
            return bean;
        }
        //find RequestMapping
        RequestMapping classRequestMapping = AnnotationUtils.findAnnotation(beanClass, RequestMapping.class);
        //2、method
        List<Method> methodList = MethodUtils.getMethodsListWithAnnotation(bean.getClass(), RequestMapping.class);
        if (methodList != null && methodList.size() <= 0) {
            return bean;
        }
        MvcInvokeMethod mvcInvokeMethod = null;
        for (Method method : methodList) {
            RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
            String[] classRequestValues = Optional.ofNullable(classRequestMapping).map(RequestMapping::value).orElse(new String[]{""});
            for (String classRequestValue : classRequestValues) {
                String[] methodRequestValues = methodRequestMapping.value();
                mvcInvokeMethod = new MvcInvokeMethod();
                for (String methodRequestValue : methodRequestValues) {
                    String fullPath = UrlUtils.fullUrlPath(classRequestValue, methodRequestValue);
                    MvcInvokeMethod mvcInvokeMethodExist = invokeMethodConcurrentHashMap.get(fullPath);
                    if (null != mvcInvokeMethodExist) {
                        throw new RuntimeException(fullPath + " is same to  " + mvcInvokeMethodExist.getBean().getClass() + " and " + bean.getClass());
                    }
                    mvcInvokeMethod.setMethod(method);
                    mvcInvokeMethod.setBean(bean);
                    invokeMethodConcurrentHashMap.put(fullPath, mvcInvokeMethod);
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName);
        return bean;
    }
}
