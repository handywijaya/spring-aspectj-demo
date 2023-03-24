package com.handy.demo.aspect.aspect;

import com.handy.demo.aspect.service.aspectfree.AuthorizationService;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Aspect
@Component
//@Order(Ordered.LOWEST_PRECEDENCE)
public class AuthorizationAspect {

  @Autowired
  private AuthorizationService authService;

  @Around("@annotation(Authorize)")
  public Mono<?> around(ProceedingJoinPoint pjp) {
    System.out.printf("GOING TO AUTHORIZE: %s, ARGS: %s%n", pjp.getSignature(),
        Arrays.toString(pjp.getArgs()));

    return authService.authorize()
        .flatMap(authorized -> {
          try {
            return ((Mono<?>) pjp.proceed())
                  .doOnNext(v -> System.out.printf("AUTHORIZE CLOSING: %s, ARGS: %s, RETURN VALUE: %s%n",
                      pjp.getSignature(), Arrays.toString(pjp.getArgs()), v));
          } catch (Throwable e) {
            e.printStackTrace();
            return Mono.error(e);
          }
        });
  }
}
