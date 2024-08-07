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
    private int typeId;
    private String name;
}
