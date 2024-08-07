package com.cyberandrey.coffeemachine.repositories;

import com.cyberandrey.coffeemachine.entities.UsageLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsageLogRepo extends JpaRepository<UsageLog, Integer> {
}
