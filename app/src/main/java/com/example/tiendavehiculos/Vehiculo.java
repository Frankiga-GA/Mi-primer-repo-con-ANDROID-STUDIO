package com.example.tiendavehiculos;

public class Vehiculo {
    private int id;
    private String marca;
    private String modelo;
    private String color;
    private String placa;

    public Vehiculo(int id, String marca, String modelo, String color, String placa) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.placa = placa;
    }

    public int getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getColor() { return color; }
    public String getPlaca() { return placa; }
}
