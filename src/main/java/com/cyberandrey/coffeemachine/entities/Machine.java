package com.cyberandrey.coffeemachine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "machine")
@Data
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
