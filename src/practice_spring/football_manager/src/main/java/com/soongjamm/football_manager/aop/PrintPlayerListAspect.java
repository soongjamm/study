package com.soongjamm.football_manager.aop;

import com.soongjamm.football_manager.components.PlayerPrinter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class PrintPlayerListAspect {

    @Autowired
    PlayerPrinter printer;

    @Pointcut("execution(public * com.soongjamm.football_manager.components.*Service ..*(..))")
    public void printTarget() {}

    @Around("printTarget()")
    public Object printPlayerList(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println();
        Object result = joinPoint.proceed();
        printer.print();
        System.out.println();

        return result;
    }
}
