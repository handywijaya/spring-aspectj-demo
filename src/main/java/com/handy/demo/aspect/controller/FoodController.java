package com.handy.demo.aspect.controller;

import com.handy.demo.aspect.dto.Food;
import com.handy.demo.aspect.service.FoodService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/foods")
public class FoodController {

  @Autowired
//  @Qualifier("PlainFoodService")
//  @Qualifier("AuthorizedFoodService")
  private FoodService service;

  @GetMapping("/")
  public Mono<List<Food>> list() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Food> get(@PathVariable("id") Integer id) {
    return service.find(id);
  }

  @GetMapping("/add")
  public Mono<Food> add() {
    return service.add();
  }

  @GetMapping("/delete")
  public Mono<Boolean> delete() {
    return service.delete();
  }
}
