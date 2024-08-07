package com.cyberandrey.coffeemachine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "coffee_type")
@Data
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

    @Override
    public String toString() {
        return "CoffeeType{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                ", machineId='" + machineId + '\'' +
                '}';
    }
}
