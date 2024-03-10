package com.first;

import org.apache.commons.text.StringEscapeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class SanitizationAspect {

    @Around("@annotation(SanitizeInput)")
    public Object sanitizeInputs(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String) {
                    args[i] = StringEscapeUtils.escapeHtml4((String) args[i]);
                    args[i] = StringEscapeUtils.escapeXml11((String) args[i]);
                }
            }
        }
        return joinPoint.proceed(args);
    }
}
