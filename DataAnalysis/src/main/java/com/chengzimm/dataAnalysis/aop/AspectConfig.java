package com.chengzimm.dataAnalysis.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * @Aspect 告诉Spring当前类是一个切面类
 */
@Component
@Aspect
public class AspectConfig {

    //抽取公共的切入点表达式
    //1,本页面引用
    //2,外页面引用
//    @Pointcut("execution(* com.chengzimm.dataAnalysis.service.impl.ModelDescInfoServiceImpl.*(..))")
    @Pointcut("execution(* com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.*(..))")
    public void pointCut(){ };

    //切入点表达式（指定在哪切入）
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName()+"方法运行...参数列表：{"+ Arrays.asList(args) +"}");
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"方法结束...");
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(Object result){
        System.out.println("方法正常返回...运行结果：{"+result+"}");
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println(joinPoint.getSignature().getName()+"方法异常...异常信息：{"+exception+"}");
    }

}



