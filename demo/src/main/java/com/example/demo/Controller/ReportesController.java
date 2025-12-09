package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Entity.UsuarioPremiumEntity;
import com.example.demo.Service.ContenidoService;
import com.example.demo.Service.ReproduccionService;
import com.example.demo.Service.UsuarioService;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {
    @Autowired
    private ContenidoService contenidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ReproduccionService reproduccionService;

    @GetMapping("/contenidos-mas-reproducidos/{n}")
    public ResponseEntity<?> contenidosMasReproducidos(@PathVariable int n) {
        return ResponseEntity.ok(contenidoService.obtenerContenidosConMasDeNReproducciones(n));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> filtrarUsuarios(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String desde,
            @RequestParam(required = false) String hasta) {

        LocalDate d1 = (desde != null && !desde.isEmpty()) ? LocalDate.parse(desde) : null;
        LocalDate d2 = (hasta != null && !hasta.isEmpty()) ? LocalDate.parse(hasta) : null;

        ArrayList<UsuarioEntity> usuarios = usuarioService.filtrarUsuarios(tipo, d1, d2);
        ArrayList<UsuarioDTO> resultado = new ArrayList<>();

        for (UsuarioEntity u : usuarios) {

            UsuarioDTO dto = new UsuarioDTO();
            dto.setNombreCompleto(u.getNombreCompleto());
            dto.setEmail(u.getEmail());
            dto.setFechaRegistro(u.getFechaRegistro());

            if (u instanceof UsuarioPremiumEntity premium) {
                dto.setTipoUsuario("premium");
                dto.setFechaMembresia(premium.getFechaInicioMembresia());
            } else {
                dto.setTipoUsuario("estandar");
                dto.setFechaMembresia(null);
            }

            resultado.add(dto);
        }

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/reproducciones-por-usuario/{usuarioId}")
    public ResponseEntity<?> reproduccionesPorUsuario(@PathVariable int usuarioId) {
        return ResponseEntity.ok(reproduccionService.obtenerReproduccionesPorUsuario(usuarioId));
    }

    @GetMapping("/promedio-calificacion/{contenidoId}")
    public ResponseEntity<?> promedioCalificacion(@PathVariable int contenidoId) {
        Double promedio = reproduccionService.promedioCalificacion(contenidoId);

        HashMap<String, Object> resultado = new HashMap<>();
        resultado.put("contenidoId", contenidoId);
        resultado.put("promedio", promedio);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/contenidos-por-fecha")
    public ResponseEntity<?> contenidosPorFecha(@RequestParam String fecha) {
        LocalDate f = LocalDate.parse(fecha);
        return ResponseEntity.ok(
                contenidoService.obtenerContenidosReproducidosEnFecha(f));
    }
}
