package com.restfull.demo.service;

import java.util.List;

import com.restfull.demo.converter.Converter;
import com.restfull.demo.entity.Nota;
import com.restfull.demo.model.MNota;
import com.restfull.demo.repository.NotaRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("service")
public class NotaService {
    
    @Autowired
    @Qualifier("repositorio")
    private NotaRepository repository;

    @Autowired
    @Qualifier("convertidor")
    private Converter converter;

    private static final Log logger = LogFactory.getLog(NotaService.class);

    public boolean crear(Nota nota){
        logger.info("CREANDO NOTA");
        try {
            repository.save(nota);
            logger.info("NOTA CREADA");
            return true;
        } catch (Exception e) {
            logger.error("OCURRIO UN ERROR");
            return false;
        }
    }

    public boolean actualizar(Nota nota){
        logger.info("ACTUALIZANDO NOTA");
        try {
            repository.save(nota);
            logger.info("NOTA ACTUALIZADA");
            return true;
        } catch (Exception e) {
            logger.error("OCURRIO UN ERROR");
            return false;
        }
    }

    public boolean borrar(String nombre, long id){
        logger.warn("BORRANDO NOTA");
        try {
            Nota nota  = repository.findByNombreAndId(nombre, id);
            repository.delete(nota);
            logger.info("NOTA BORRADA");
            return true;
        } catch (Exception e) {
            logger.error("OCURRIO UN ERROR");
            return false;
        }
    }

    public List<MNota> obtener(){
        return converter.covertirLista(repository.findAll());
    }

    public MNota obtenerPorNombreTitulo(String nombre, String titulo){
        return new MNota(repository.findByNombreAndTitulo(nombre, titulo));
    }

    public List<MNota> obtenerTitulo(String titulo){
        return converter.covertirLista(repository.findByTitulo(titulo));
    }

    public List<MNota> obtenerPorPaginacion(Pageable pageable){
        return converter.covertirLista(repository.findAll(pageable).getContent());
    }
}
