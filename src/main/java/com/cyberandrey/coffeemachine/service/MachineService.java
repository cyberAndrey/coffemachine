package com.cyberandrey.coffeemachine.service;

import com.cyberandrey.coffeemachine.entities.Machine;

public interface MachineService {
    Machine onMachine(Integer id) throws NullPointerException;
    String pourCoffee(Machine machine, Integer coffeeId) throws NullPointerException;
    String checkState();
    void refreshMachines();
}
