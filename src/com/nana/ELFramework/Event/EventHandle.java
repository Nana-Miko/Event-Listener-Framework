package com.nana.ELFramework.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface EventHandle {
    int LOWEST = 4;
    int LOW = 3;
    int MIDDLE = 2;
    int HIGH = 1;
    int HIGHEST = 0;

    int priority() default MIDDLE;
}
