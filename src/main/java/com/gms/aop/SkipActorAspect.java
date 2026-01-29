package com.gms.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipActorAspect {
    // Marker to skip the aspect
}
