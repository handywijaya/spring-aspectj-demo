package com.handy.demo.aspect.service.aspectfree;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthorizationService {

  private static final AtomicInteger INDEX = new AtomicInteger(0);

  public Mono<Boolean> authorize() {
    if (INDEX.getAndIncrement() % 2 != 0) {
      return Mono.error(new RuntimeException("Unauthorized"));
    }
    return Mono.just(true);
  }
}
