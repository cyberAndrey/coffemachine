package com.cyberandrey.coffeemachine;

import static org.assertj.core.api.Assertions.assertThat;

import com.cyberandrey.coffeemachine.configuration.Strings;
import com.cyberandrey.coffeemachine.controller.MachineController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class CoffeeMachineApplicationTests {
    @Autowired
    private MachineController machineController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(machineController).isNotNull();
    }

    @Test
    void machineRefreshTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/machine/refresh")
                .accept(MediaTypes.HAL_JSON)).andReturn();
        Assertions.assertEquals(Strings.UPDATED, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void machineDevastated() throws Exception {
        // включаем первую машину
        mockMvc.perform(MockMvcRequestBuilders.get("/machine/on/1")
                .accept(MediaTypes.HAL_JSON)).andReturn();
        // делаем кофе пока есть вода
        MvcResult doCoffee = null;
        for (int i = 0; i <10; i++) {
            doCoffee = mockMvc.perform(MockMvcRequestBuilders.get("/machine/pour/3")
                    .accept(MediaTypes.HAL_JSON)).andReturn();
        }
        // Проверяем что вода закончилась
        Assertions.assertEquals(Strings.NOT_WATER, doCoffee.getResponse().getContentAsString());
    }
}
