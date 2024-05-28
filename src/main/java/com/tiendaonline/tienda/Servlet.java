package com.tiendaonline.tienda;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        /*Obtenemos los productos*/
        String nombre = request.getParameter("nombre");
        String preciotr = request.getParameter("precio");
        String cantidades = request.getParameter("cantidades");
       if (nombre != null && preciotr != null) {
           double precio = Double.parseDouble(preciotr);
           int cantidades2 =1;

           /*Creamos el objeto para que contenga en el carrtio*/
           StringBuilder listado = new StringBuilder();
           listado.append("<li>").append(nombre).append("-$").append(precio).append("</li>");

           /*Se agrupa y se conjunta en el carrito*/
           HttpSession session = request.getSession();
           StringBuilder carritos = (StringBuilder) session.getAttribute("carritos"); ;
           if(carritos==null){
               carritos = new StringBuilder();
           }
           carritos.append(listado);

           /*Seteamos los valores*/
           double valorActual =0;
            int cantidadActual =0;
           String valorTotal= (String) session.getAttribute("total");
           if(valorTotal!=null){
               valorActual = Double.parseDouble(valorTotal);
           }
           if(cantidades!=null){
               cantidades2 = Integer.parseInt(cantidades);
           }
           double ValorFinal = valorActual + (precio*cantidades2);
           int cantidadFInal = cantidadActual + cantidades2;
           session.setAttribute("total", String.valueOf(ValorFinal));
           session.setAttribute("cantidades", String.valueOf(cantidadFInal));
           /*Mostramos todos los items*/
           out.print("<!DOCTYPE html>");
           out.print("<html>");
           out.print("<head>");
           out.printf("<meta charset=\"utf-8\">");
           out.printf("<title> Hola Mundo </title>");
           out.print("</head>");
           out.print("<body>");
           out.print("<h1>Facturas </h1>");
           out.print("<ul>");
           out.println("<li>"+nombre+"</li>");
           out.println("<li> la cantidad de producto"+ nombre + "que esta  llevando actualmente :"+cantidadFInal+"</li>");
           out.println("<li> Valor Unitario"+preciotr+"</li>");
           out.println("<li> Valor Total"+ValorFinal+"</li>");
           out.print("</body>");
           out.print("</html>");
       }




    }
}
