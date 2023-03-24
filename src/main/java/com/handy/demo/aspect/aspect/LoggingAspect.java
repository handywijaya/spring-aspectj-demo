package com.handy.demo.aspect.aspect;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoggingAspect {

  @Pointcut("within(com.handy.demo.aspect.service.*) "
      + "&& !within(com.handy.demo.aspect.service.aspectfree.*) ")
  public void pointcut() {
  }

  @Before("pointcut()")
  public void before(JoinPoint jp) {
    System.out.printf("BEFORE METHOD: %s, ARGS: %s%n", jp.getSignature(),
        Arrays.toString(jp.getArgs()));
  }

  @AfterReturning(value = "pointcut()", returning = "returnValue")
  public void after(JoinPoint jp, Object returnValue) {
    System.out.printf("AFTER METHOD: %s, ARGS: %s, RETURN VALUE: %s%n",
        jp.getSignature(), Arrays.toString(jp.getArgs()), returnValue);
  }

//  @Around("pointcut()")
//  public Mono<?> around(ProceedingJoinPoint pjp) {
//    System.out.printf("BEFORE METHOD: %s, ARGS: %s%n", pjp.getSignature(),
//        Arrays.toString(pjp.getArgs()));
//
//    try {
//      return ((Mono<?>) pjp.proceed())
//          .doOnNext(v -> System.out.printf("AFTER METHOD: %s, ARGS: %s, RETURN VALUE: %s%n",
//              pjp.getSignature(), Arrays.toString(pjp.getArgs()), v));
//    } catch (Throwable e) {
//      e.printStackTrace();
//      return Mono.error(e);
//    }
//  }
}
