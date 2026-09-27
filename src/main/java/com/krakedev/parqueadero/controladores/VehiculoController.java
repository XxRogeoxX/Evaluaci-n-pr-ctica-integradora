package com.krakedev.parqueadero.controladores;

import com.krakedev.parqueadero.modelo.Auto;
import com.krakedev.parqueadero.modelo.Motocicleta;
import com.krakedev.parqueadero.modelo.Vehiculo;
import com.krakedev.parqueadero.servicios.ServicioVehiculos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final ServicioVehiculos servicioVehiculos;

    public VehiculoController(ServicioVehiculos servicioVehiculos) {
        this.servicioVehiculos = servicioVehiculos;
    }

    @PostMapping("/auto")
    public ResponseEntity<?> registrarAuto(@RequestBody Auto auto) {
        boolean ingresado = servicioVehiculos.ingresarVehiculo(auto);
        if (ingresado) {
            return ResponseEntity.status(HttpStatus.CREATED).body(auto);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("No se pudo ingresar el auto: cupo lleno o placa duplicada");
    }

    @PostMapping("/moto")
    public ResponseEntity<?> registrarMoto(@RequestBody Motocicleta moto) {
        boolean ingresado = servicioVehiculos.ingresarVehiculo(moto);
        if (ingresado) {
            return ResponseEntity.status(HttpStatus.CREATED).body(moto);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("No se pudo ingresar la moto: cupo lleno o placa duplicada");
    }

    @GetMapping
    public ArrayList<Vehiculo> listarVehiculos() {
        return servicioVehiculos.listarVehiculos();
    }

    @GetMapping("/{placa}")
    public ResponseEntity<?> buscarPorPlaca(@PathVariable String placa) {
        Vehiculo vehiculo = servicioVehiculos.buscarPorPlaca(placa);
        if (vehiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un vehiculo con placa " + placa);
        }
        return ResponseEntity.ok(vehiculo);
    }
}