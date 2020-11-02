package com.restfull.demo.controller;

import com.restfull.demo.entity.Nota;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/nota")
public class ClienteRest {
    
    private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJEcmFnbyJ9.4i5l0FAmUGS4P4FABr5Y2L2G8vT0KYaWnyR5uYIHIWpkE-p1wBvWVX0KF57xL0DJBpFJBF1nz-5P4l2Wg034Wg";

    @GetMapping("/all")
    public ModelAndView devolverTodos(){
        ModelAndView mav = new ModelAndView("template");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", token);

        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<Nota[]> notasEntity = restTemplate.exchange("http://localhost:8090/v1/notas", HttpMethod.GET, entity, Nota[].class);

        Nota[] notas = notasEntity.getBody();

        mav.addObject("notas", notas);

        return mav;
    }
}
