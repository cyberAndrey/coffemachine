package com.cyberandrey.coffeemachine.controller;

import com.cyberandrey.coffeemachine.configuration.Strings;
import com.cyberandrey.coffeemachine.entities.CoffeeType;
import com.cyberandrey.coffeemachine.entities.Machine;
import com.cyberandrey.coffeemachine.entities.MachineInfo;
import com.cyberandrey.coffeemachine.entities.UsageLog;
import com.cyberandrey.coffeemachine.repositories.MachineInfoRepo;
import com.cyberandrey.coffeemachine.repositories.MachineRepo;
import com.cyberandrey.coffeemachine.repositories.UsageLogRepo;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/machine")
public class MachineController {

    private final MachineRepo machineRepo;
    private final MachineInfoRepo machineInfoRepo;
    private final UsageLogRepo usageLogRepo;
    Machine currentWork;

    public MachineController(MachineRepo machineRepo, MachineInfoRepo machineInfoRepo, UsageLogRepo usageLogRepo) {
        this.machineRepo = machineRepo;
        this.machineInfoRepo = machineInfoRepo;
        this.usageLogRepo = usageLogRepo;
        this.currentWork = null;
    }

    private static record Coffee(String name, int volume) {}

    @GetMapping("on/{id}")
    public String onMachine(@PathVariable Integer id) {
        if (currentWork != null)
            return Strings.ALREADY_ON;
        Optional<Machine> machine = machineRepo.findById(id);
        if (machine.isPresent()) {
            currentWork = machine.get();
            return currentWork.toString();
        }
        return Strings.NOT_FOUND;
    }

    @GetMapping("/off")
    public String offMachine() {
        if (currentWork != null) {
            String workerName = currentWork.getName();
            this.currentWork = null;
            return workerName + Strings.TURNED_OFF;
        }
        return Strings.ALREADY_OFF;
    }

    @GetMapping("/pour/{id}")
    public String pourCoffee(@PathVariable Integer id) {
        if (currentWork != null) {
            var desiredCoffee = currentWork.getCoffeeTypes().stream().filter(ct -> ct.getTypeId() == id).findFirst();
            if (desiredCoffee.isPresent()) {
                CoffeeType coffeeType = desiredCoffee.get();
                String message = "";
                if (currentWork.getWater() < coffeeType.getVolume())
                    message += Strings.NOT_WATER;
                if (currentWork.getBeans() < coffeeType.getSubstance())
                    message += Strings.NOT_BEANS;
                if (message.isEmpty()) {
                    currentWork.setWater(currentWork.getWater()-coffeeType.getVolume());
                    currentWork.setBeans(currentWork.getBeans()-coffeeType.getSubstance());
                    machineRepo.save(currentWork);
                    logAction(coffeeType.toString());
                    return new Coffee(coffeeType.getName(), coffeeType.getVolume()).toString();
                }
                return message;
            }
            return Strings.UNAVAILABLE_TYPE;
        }
        return Strings.ALREADY_OFF;
    }

    @GetMapping("/info")
    public String checkState() {
        var machines = machineRepo.findAll();
        String state = "";
        for (Machine machine : machines) {
            state += machine.toString() + "\n";
        }
        return state;
    }

    @GetMapping("/refresh")
    public String refreshMachines() {
        var machines = machineRepo.findAll();
        for (Machine machine : machines) {
            MachineInfo machineInfo = machineInfoRepo.getReferenceById(machine.getId());
            machine.setWater(machineInfo.getWater());
            machine.setBeans(machineInfo.getBeans());
            machineRepo.save(machine);
            logAction(Strings.UPDATED);
        }
        return Strings.UPDATED;
    }

    private void logAction(String action) {
        UsageLog usageLog = new UsageLog();
        usageLog.setAction(action);
        usageLog.setTimestamp(Instant.now());
        usageLogRepo.save(usageLog);
    }
}
