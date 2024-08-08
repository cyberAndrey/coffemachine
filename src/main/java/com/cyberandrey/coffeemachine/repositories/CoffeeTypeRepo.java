package com.cyberandrey.coffeemachine.repositories;

import com.cyberandrey.coffeemachine.entities.CoffeeType;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface CoffeeTypeRepo extends JpaRepository<CoffeeType, Integer> {
}
