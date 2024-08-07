package com.cyberandrey.coffeemachine.repositories;

import com.cyberandrey.coffeemachine.entities.CoffeeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CoffeeTypeRepo extends JpaRepository<CoffeeType, Integer> {
}
