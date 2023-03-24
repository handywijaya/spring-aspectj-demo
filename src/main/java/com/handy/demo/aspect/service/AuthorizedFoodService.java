package com.handy.demo.aspect.service;

import com.handy.demo.aspect.aspect.Authorize;
import com.handy.demo.aspect.dto.Food;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("AuthorizedFoodService")
public class AuthorizedFoodService extends FoodService {

  @Authorize
  @Override
  public Mono<Food> find(Integer id) {
    return super.find(id);
  }
}
