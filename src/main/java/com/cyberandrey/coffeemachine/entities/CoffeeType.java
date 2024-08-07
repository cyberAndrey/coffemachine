package com.cyberandrey.coffeemachine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "coffee_type")
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int typeId;
    private String name;
    @Column(name = "machine_id")
    private Integer machineId;
    private Integer volume;
    private Integer substance;

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getSubstance() {
        return substance;
    }

    public void setSubstance(Integer substance) {
        this.substance = substance;
    }

    @Override
    public String toString() {
        return "CoffeeType{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                '}';
    }
}
