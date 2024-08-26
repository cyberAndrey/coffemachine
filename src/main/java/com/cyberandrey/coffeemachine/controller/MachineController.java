package com.cyberandrey.coffeemachine.controller;

import com.cyberandrey.coffeemachine.configuration.Strings;
import com.cyberandrey.coffeemachine.entities.CoffeeType;
import com.cyberandrey.coffeemachine.entities.Machine;
import com.cyberandrey.coffeemachine.entities.MachineInfo;
import com.cyberandrey.coffeemachine.entities.UsageLog;
import com.cyberandrey.coffeemachine.repositories.MachineInfoRepo;
import com.cyberandrey.coffeemachine.repositories.MachineRepo;
import com.cyberandrey.coffeemachine.repositories.UsageLogRepo;
import com.cyberandrey.coffeemachine.service.MachineService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/machine")
public class MachineController {
    private final MachineService machineService;
    Machine currentWork;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
        this.currentWork = null;
    }

    @GetMapping("on/{id}")
    public String onMachine(@PathVariable Integer id) {
        if (currentWork != null)
            return Strings.ALREADY_ON;
        try {
            currentWork = machineService.onMachine(id);
            return currentWork.toString();
        } catch (NullPointerException e) {
            return e.getMessage();
        }
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
            try {
                return machineService.pourCoffee(currentWork, id);
            } catch (NullPointerException e) {
                return e.getMessage();
            }
        }
        return Strings.ALREADY_OFF;
    }

    @GetMapping("/info")
    public String checkState() {
        return machineService.checkState();
    }

    @GetMapping("/refresh")
    public String refreshMachines() {
        try {
            machineService.refreshMachines();
            return Strings.UPDATED;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
