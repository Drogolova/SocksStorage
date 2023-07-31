package com.example.socksStorage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private Color color;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private Size size;
    @Column
    private int cottonPart;
    @Column
    private int quantity;

    @Override
    public String toString() {
        return "Socks{" +
                "id=" + id +
                ", color=" + color +
                ", size=" + size +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
