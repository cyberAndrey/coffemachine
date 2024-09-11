package com.cyberandrey.coffeemachine.repositories;

import com.cyberandrey.coffeemachine.entities.Machine;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Hidden
public interface MachineRepo extends JpaRepository<Machine, Integer> {
    @EntityGraph(attributePaths = {"coffeeTypes"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Machine> findAll();
}
