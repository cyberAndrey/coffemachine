package com.cyberandrey.coffeemachine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "machine_info")
@AllArgsConstructor
@NoArgsConstructor
public class MachineInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int water;
    private int beans;

    public int getId() {
        return id;
    }

    public int getWater() {
        return water;
    }

    public int getBeans() {
        return beans;
    }
}
