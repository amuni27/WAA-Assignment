package com.waa.assignment.aop;

import com.waa.assignment.annotation.ExecutionTime;
import com.waa.assignment.domain.Logger;
import com.waa.assignment.repository.LoggerRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.weaver.patterns.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Aspect
@Component
public class aop {

    @Autowired
    private LoggerRepository loggerRepository;

    @Around("execution(* com.waa.assignment.controller.*.*(..))")
    public void addEventToLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("AOP: Logging event");
        Object result = joinPoint.proceed();
        Logger logger= new Logger(LocalDate.now(), LocalDateTime.now(),"Admin",joinPoint.getSignature().getName());
        loggerRepository.save(logger);
    }

    @Around("@annotation(executionTime)")
    public Object calculateExecutionTime(ProceedingJoinPoint joinPoint, ExecutionTime executionTime) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTimeMillis = endTime - startTime;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTimeMillis + "ms");

        return result;
    }
}
