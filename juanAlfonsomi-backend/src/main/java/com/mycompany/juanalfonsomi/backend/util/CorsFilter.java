/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.util;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*") // Este filtro se aplica a TODAS las rutas de la API
public class CorsFilter implements Filter {
    
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletResponse response = (HttpServletResponse) res;
        
        // 1. Permitimos que Angular (puerto 4200) acceda a los datos
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        
        // 2. Permitimos los métodos de envío de datos necesarios
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        
        // 3. Permitimos que se envíen cabeceras JSON
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // 4. Continuamos con la ejecución normal del Servlet
        chain.doFilter(req, res);
    }
}
