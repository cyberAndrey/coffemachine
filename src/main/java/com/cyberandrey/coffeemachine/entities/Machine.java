package com.cyberandrey.coffeemachine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "machine")
@AllArgsConstructor
@NoArgsConstructor
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Integer water;
    private Integer beans;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_id")
    private List<CoffeeType> coffeeTypes;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public Integer getBeans() {
        return beans;
    }

    public void setBeans(Integer beans) {
        this.beans = beans;
    }

    public List<CoffeeType> getCoffeeTypes() {
        return coffeeTypes;
    }

    public void setCoffeeTypes(List<CoffeeType> coffeeTypes) {
        this.coffeeTypes = coffeeTypes;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "name='" + name + '\'' +
                ", water=" + water +
                ", beans=" + beans +
                ", coffeeTypes=" + coffeeTypes +
                '}';
    }
}
