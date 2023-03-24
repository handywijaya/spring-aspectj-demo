package com.handy.demo.aspect.service;

import com.handy.demo.aspect.dto.Food;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Primary
public class FoodService {

  public static Integer SEQUENCE = 1;
  public static Map<Integer, Food> FOOD_DB = new HashMap<>();

  public Mono<List<Food>> findAll() {
    return Mono.just(new ArrayList<>(FOOD_DB.values()));
  }

  public Mono<Food> find(Integer id) {
    return Mono.just(FOOD_DB.get(id));
  }

  public Mono<Food> add() {
    Food newFood = new Food(SEQUENCE, "Food-" + SEQUENCE);
    FOOD_DB.put(SEQUENCE++, newFood);
    return Mono.just(newFood);
  }

  public Mono<Boolean> delete() {
    FOOD_DB.remove(SEQUENCE--);
    return Mono.just(true);
  }
}
