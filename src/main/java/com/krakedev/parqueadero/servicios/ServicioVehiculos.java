package com.krakedev.parqueadero.servicios;

import com.krakedev.parqueadero.modelo.Vehiculo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioVehiculos {

    private ArrayList<Vehiculo> parqueadero = new ArrayList<>();
    private final int CAPACIDAD_MAXIMA = 10;

    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo v : parqueadero) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    public boolean ingresarVehiculo(Vehiculo vehiculo) {
        if (parqueadero.size() >= CAPACIDAD_MAXIMA) {
            return false;
        }
        if (buscarPorPlaca(vehiculo.getPlaca()) != null) {
            return false;
        }
        return parqueadero.add(vehiculo);
    }

    public Vehiculo retirarVehiculo(String placa) {
        Vehiculo encontrado = buscarPorPlaca(placa);
        if (encontrado != null) {
            parqueadero.remove(encontrado);
            return encontrado;
        }
        return null;
    }

    public ArrayList<Vehiculo> listarVehiculos() {
        return parqueadero;
    }
}
