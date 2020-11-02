package com.restfull.demo.converter;

import java.util.ArrayList;
import java.util.List;

import com.restfull.demo.entity.Nota;
import com.restfull.demo.model.MNota;

import org.springframework.stereotype.Component;

@Component("convertidor")
public class Converter {
    
    public List<MNota> covertirLista(List<Nota> notas){
        List<MNota> mnotas = new ArrayList<>();

        for(Nota nota : notas){
            mnotas.add(new MNota(nota));
        }
        return mnotas;
    }
}
