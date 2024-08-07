package com.cyberandrey.coffeemachine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "usage_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private CoffeeType coffeeType;

    @Override
    public String toString() {
        return "UsageLog{" +
                "timestamp=" + timestamp +
                ", coffeeType=" + coffeeType +
                '}';
    }
}
