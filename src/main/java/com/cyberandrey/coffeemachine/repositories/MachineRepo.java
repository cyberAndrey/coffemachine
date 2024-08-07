package com.cyberandrey.coffeemachine.repositories;

import com.cyberandrey.coffeemachine.entities.Machine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MachineRepo extends JpaRepository<Machine, Integer> {
}
