package com.example.socksStorage.dto;

import com.example.socksStorage.entity.Color;
import com.example.socksStorage.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SocksDTO {
    //артикул
    private Long partNumber;
    private Color color;
    private Size size;
    private int cottonPart;
    private int quantity;
}

