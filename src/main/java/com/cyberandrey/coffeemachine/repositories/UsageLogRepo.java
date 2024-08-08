package com.cyberandrey.coffeemachine.repositories;

import com.cyberandrey.coffeemachine.entities.UsageLog;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface UsageLogRepo extends JpaRepository<UsageLog, Integer> {
}
