package com.courseapp.backend.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckParamsValidity {
    private static final Logger logger = LoggerFactory.getLogger(CheckParamsValidity.class);

    @Pointcut("execution (* com.courseapp.backend.controllers.RecipeController.getALlRecipes(..))")
    private void pointcutForGetAllRecipesMethod() {}

    @Before("pointcutForGetAllRecipesMethod()")
    public void checkParamsTypes(JoinPoint jp) {
        int pageSize = (int) jp.getArgs()[0];
        if (pageSize < 0) {
            throw new IllegalArgumentException();
        }
        logger.info("checkParamsTypes");
    }
}
