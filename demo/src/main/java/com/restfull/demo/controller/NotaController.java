package com.restfull.demo.controller;

import java.util.List;

import com.restfull.demo.entity.Nota;
import com.restfull.demo.model.MNota;
import com.restfull.demo.service.NotaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/v1")
public class NotaController {
    
    @Autowired
    @Qualifier("service")
    NotaService nService;

    @PutMapping("/nota")
    public boolean agregarNota(@RequestBody Nota nota){
        return nService.crear(nota);
    }

    @PostMapping("/nota")
    public boolean ctualizarNota(@RequestBody Nota nota){
        return nService.actualizar(nota);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/nota/{id}/{nombre}")
    public boolean borrarNota(@PathVariable ("id") long id, @PathVariable("nombre") String nombre){
        return nService.borrar(nombre, id);
    }

    @GetMapping("/notas")
    public List<MNota> obtenerNotas(Pageable pageable){
        return nService.obtenerPorPaginacion(pageable);
    }
}
