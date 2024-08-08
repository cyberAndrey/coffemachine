package com.cyberandrey.coffeemachine.controller;

import com.cyberandrey.coffeemachine.entities.UsageLog;
import com.cyberandrey.coffeemachine.repositories.UsageLogRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/log")
public class UsageController {
    private final UsageLogRepo usageLogRepo;

    public UsageController(UsageLogRepo usageLogRepo) {
        this.usageLogRepo = usageLogRepo;
    }
    @GetMapping("/{limit}")
    public String getLog(@PathVariable Integer limit) {
        var page = usageLogRepo.findAll(PageRequest.of(0, limit));
        var elements = page.stream().map(UsageLog::toString).toArray();
        return Arrays.toString(elements);
    }
}
