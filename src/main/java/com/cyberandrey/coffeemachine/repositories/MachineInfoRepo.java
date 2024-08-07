package com.cyberandrey.coffeemachine.repositories;

import com.cyberandrey.coffeemachine.entities.MachineInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineInfoRepo extends JpaRepository<MachineInfo, Integer> {
}
