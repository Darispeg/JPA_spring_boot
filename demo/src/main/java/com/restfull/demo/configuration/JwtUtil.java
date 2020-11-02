package com.restfull.demo.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collections;

public class JwtUtil {
    
    // Metodo para crear el JWT y enviarlo al cliente en el header de la respuesta
    static void addAuthentication(HttpServletResponse response, String username){

        String token = Jwts.builder().setSubject(username)
        
            /* 
            Hash con el que firmamos la clave*/
            .signWith(SignatureAlgorithm.HS512, "P@titO")
            .compact();
        
        // Agregamos al encabezado el token
        response.addHeader("Authorization", "Bearer " + token);
    }

    // Metodo para validar el token enviado por cliente
    static Authentication getAuthentication(HttpServletRequest request){

        //Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");

        // si hay un token presente, entonces lo validamos
        if(token != null){
            String user = Jwts.parser()
                    .setSigningKey("P@titO")
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody()
                    .getSubject();
            
            /*
            Recordamos que para las demas peticiones que no sean /login
            no requerimos una autenticacion por user/password
            por este motivo podemos devolver un UsernamePasswordAithenticationToken sin Password
            */

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : 
                    null;
        }
        return null; 
    }
}
