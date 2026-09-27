package com.krakedev.parqueadero.controladores;

import com.krakedev.parqueadero.modelo.TicketCobro;
import com.krakedev.parqueadero.servicios.ServicioCobro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cobros")
public class CobroController {

    private final ServicioCobro servicioCobro;

    public CobroController(ServicioCobro servicioCobro) {
        this.servicioCobro = servicioCobro;
    }

    @PostMapping("/procesar/{placa}/{horas}")
    public ResponseEntity<?> procesarSalida(@PathVariable String placa, @PathVariable int horas) {
        TicketCobro ticket = servicioCobro.procesarSalida(placa, horas);
        if (ticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un vehiculo con placa " + placa + " en el parqueadero");
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/total")
    public double totalRecaudado() {
        return servicioCobro.calcularTotalRecaudado();
    }

    @GetMapping("/historial")
    public ArrayList<TicketCobro> historial() {
        return servicioCobro.listarTickets();
    }
}