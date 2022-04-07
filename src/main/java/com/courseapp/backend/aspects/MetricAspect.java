package com.courseapp.backend.aspects;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetricAspect {
    private final Timer timer;

    public MetricAspect(final MeterRegistry registry) {
        timer = registry.timer("aspects.project.timer");
    }

    @Around("execution (* com.courseapp.backend.services.BaseServiceImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint jp) {
        return timer.record( ()-> {
            try {
                return jp.proceed();
            }
            catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }
}
