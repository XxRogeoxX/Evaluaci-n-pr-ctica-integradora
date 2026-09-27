package com.krakedev.parqueadero.modelo;

public class Motocicleta extends Vehiculo {

    private int cilindraje;

    protected Motocicleta() {
        super();
    }

    public Motocicleta(String placa, String propietario, int cilindraje) {
        super(placa, propietario);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    @Override
    public double calcularTarifa(int horasPermanencia) {
        double tarifaPorHora = (cilindraje > 250) ? 1.00 : 0.75;
        return horasPermanencia * tarifaPorHora;
    }

    @Override
    public String toString() {
        return "Motocicleta{" +
                "placa='" + getPlaca() + '\'' +
                ", propietario='" + getPropietario() + '\'' +
                ", cilindraje=" + cilindraje +
                ", horaIngreso=" + getHoraIngreso() +
                '}';
    }
}