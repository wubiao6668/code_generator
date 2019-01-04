package com.generator.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
public @interface FieldDescAnnotation {
    String comment = "";
    int minLength = Integer.MIN_VALUE;
    int maxLength = Integer.MAX_VALUE;
    String defaultValue = "";
    String[] values = null;

}
