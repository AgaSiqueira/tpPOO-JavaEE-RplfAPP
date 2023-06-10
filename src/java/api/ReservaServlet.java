/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package api;

import classes.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author agath
 */
@WebServlet(name = "ReservaServlet", urlPatterns = {"/reservar"})
public class ReservaServlet extends HttpServlet {

    private JSONObject getJSONBody(BufferedReader reader) throws Exception{
         StringBuilder buffer = new StringBuilder();
         String line = null;
         while((line = reader.readLine()) != null){
             buffer.append(line);
         }
         return new JSONObject(buffer.toString());
     }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset = utf-8");
        JSONObject file = new JSONObject();
       try{
           file.put("Reservas", new JSONArray(Reserva.livrosReservados));
       }catch(Exception ex){
           response.setStatus(500);
           file.put("error", ex.getLocalizedMessage());
       }
       response.getWriter().print(file.toString());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset = utf-8");
        JSONObject file = new JSONObject();
       try{
           JSONObject body = getJSONBody(request.getReader());
           //pegando valores
           Random random = new Random();
           int cdLivroReserva=0, cdReserva=0, cdAlugadorReserva=0;
           cdLivroReserva = body.getInt("cdLivroReserva");
           cdReserva = random.nextInt(10000);
           cdAlugadorReserva = body.getInt("cdAlugadorReserva");
           //data
           String devolucao = body.getString("devolucao");
           LocalDate data = LocalDate.parse(devolucao);
           data = data.plusDays(20);//add 20 dias
           Date dataDevolucao = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());

           if(cdLivroReserva != 0 && cdReserva != 0 && cdAlugadorReserva != 0 && 
                   dataDevolucao != null){
               Reserva reservar = new Reserva(cdLivroReserva, cdReserva, 
                       cdAlugadorReserva, dataDevolucao);
               Reserva.livrosReservados.add(reservar);
           }
           file.put("Reservas", new JSONArray(Reserva.livrosReservados));
       }catch(Exception ex){
           response.setStatus(500);
           file.put("error", ex.getLocalizedMessage());
       }
       response.getWriter().print(file.toString());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       response.setContentType("application/json;charset = utf-8");
        JSONObject file = new JSONObject();
        try{
            int identificador = Integer.parseInt(request.getParameter("cdReserva"));
            Reserva reservaAtualizada = null;
            for (Reserva reservar : Reserva.livrosReservados) {
                if (reservar.getCdReserva() == identificador) {
                    reservaAtualizada = reservar;
                    break;
                }
            }
            if (reservaAtualizada != null) {
                JSONObject body = getJSONBody(request.getReader());
                if (body.has("cdLivroReserva")) {
                    reservaAtualizada.setCdLivroReserva(body.getInt("cdLivroReserva"));
                }
                if (body.has("cdAlugadorReserva")) {
                    reservaAtualizada.setCdAlugadorReserva(body.getInt("cdAlugadorReserva"));
                }
                int index = Reserva.livrosReservados.indexOf(reservaAtualizada);
                Reserva.livrosReservados.set(index, reservaAtualizada);
                file.put("Reservas", new JSONArray(Reserva.livrosReservados));
            } else {
                response.setStatus(404);
                file.put("error", "Reserva não encontrado");
            }
        }catch(Exception ex){
           response.setStatus(500);
           file.put("error", ex.getLocalizedMessage());
       }
       response.getWriter().print(file.toString());
    }
    
    
    
    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json;charset = utf-8");
        JSONObject file = new JSONObject();
       try{
           int identificadorReserva = Integer.parseInt(request.getParameter("cdReserva"));

           int reservaRetirada = -1;
           for(Reserva r: Reserva.livrosReservados){
               if(r.getCdReserva() == identificadorReserva){
                reservaRetirada= Reserva.livrosReservados.indexOf(r);
                break;
               }
           }
           if (reservaRetirada>-1) Reserva.livrosReservados.remove(reservaRetirada);
           file.put("Reservas", new JSONArray(Reserva.livrosReservados));
       }catch(Exception ex){
           response.setStatus(500);
           file.put("error", ex.getLocalizedMessage());
       }
       response.getWriter().print(file.toString());
    }
    
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
