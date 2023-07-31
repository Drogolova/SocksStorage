package com.example.socksStorage.entity;

public enum Color {
    RED("красный"),
    WHITE("белый"),
    BLACK("чёрный"),
    BLUE("синий"),
    YELLOW("жёлтый"),
    GREEN("зелёный"),
    BROWN("коричневый"),
    GREY("серый");

    public final String color;

    Color(String color) {

        this.color = color;
    }
}
