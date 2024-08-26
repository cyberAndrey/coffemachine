package com.cyberandrey.coffeemachine.service;

import com.cyberandrey.coffeemachine.configuration.Strings;
import com.cyberandrey.coffeemachine.controller.MachineController;
import com.cyberandrey.coffeemachine.entities.CoffeeType;
import com.cyberandrey.coffeemachine.entities.Machine;
import com.cyberandrey.coffeemachine.entities.MachineInfo;
import com.cyberandrey.coffeemachine.entities.UsageLog;
import com.cyberandrey.coffeemachine.repositories.MachineInfoRepo;
import com.cyberandrey.coffeemachine.repositories.MachineRepo;
import com.cyberandrey.coffeemachine.repositories.UsageLogRepo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class MachineServiceImpl implements MachineService {
    private final MachineRepo machineRepo;
    private final MachineInfoRepo machineInfoRepo;
    private final UsageLogRepo usageLogRepo;

    public MachineServiceImpl(MachineRepo machineRepo, MachineInfoRepo machineInfoRepo, UsageLogRepo usageLogRepo) {
        this.machineRepo = machineRepo;
        this.machineInfoRepo = machineInfoRepo;
        this.usageLogRepo = usageLogRepo;
    }

    private static record Coffee(String name, int volume) {}

    @Override
    public Machine onMachine(Integer id) {
        Optional<Machine> machine = machineRepo.findById(id);
        if (machine.isPresent()) {
            return machine.get();
        }
        throw new NullPointerException(Strings.NOT_FOUND);
    }

    @Override
    public String pourCoffee(Machine machine, Integer coffeeId) {
        var desiredCoffee = machine.getCoffeeTypes().stream().filter(ct -> ct.getTypeId() == coffeeId).findFirst();
        if (desiredCoffee.isPresent()) {
            CoffeeType coffeeType = desiredCoffee.get();
            String message = "";
            if (machine.getWater() < coffeeType.getVolume())
                message += Strings.NOT_WATER;
            if (machine.getBeans() < coffeeType.getSubstance())
                message += Strings.NOT_BEANS;
            if (message.isEmpty()) {
                machine.setWater(machine.getWater()-coffeeType.getVolume());
                machine.setBeans(machine.getBeans()-coffeeType.getSubstance());
                machineRepo.save(machine);
                logAction(coffeeType.toString());
                return new Coffee(coffeeType.getName(), coffeeType.getVolume()).toString();
            }
            return message;
        }
        throw new NullPointerException(Strings.UNAVAILABLE_TYPE);
    }

    @Override
    public String checkState() {
        var machines = machineRepo.findAll();
        String state = "";
        for (Machine machine : machines) {
            state += machine.toString() + "\n";
        }
        return state;
    }

    @Override
    public void refreshMachines() {
        var machines = machineRepo.findAll();
        for (Machine machine : machines) {
            MachineInfo machineInfo = machineInfoRepo.getReferenceById(machine.getId());
            machine.setWater(machineInfo.getWater());
            machine.setBeans(machineInfo.getBeans());
            machineRepo.save(machine);
            logAction(Strings.UPDATED);
        }
    }

    private void logAction(String action) {
        UsageLog usageLog = new UsageLog();
        usageLog.setAction(action);
        usageLog.setTimestamp(Instant.now());
        usageLogRepo.save(usageLog);
    }
}
