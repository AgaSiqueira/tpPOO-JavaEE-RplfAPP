/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package api;

import classes.Livro;
import classes.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset = utf-8");
        JSONObject file = new JSONObject();
       try{
           if(request.getParameter("cdReserva") != null) {
                int identificador = Integer.parseInt(request.getParameter("cdReserva"));
                Reserva r = Reserva.getReserva(identificador);
                JSONObject o = new JSONObject();
                o.put("cdReserva", r.getCdReserva());
                o.put("cdLivroReserva", r.getCdLivroReserva());
                o.put("cdAlugadorReserva", r.getCdAlugadorReserva());
                o.put("devolucao", r.getDevolucao());
                file.put("Reservas", o);
           }
           else {
                ArrayList<Reserva> list = Reserva.getReservas();
                JSONArray arr = new JSONArray();
                for(Reserva r : list) {
                    JSONObject o = new JSONObject();
                    o.put("cdReserva", r.getCdReserva());
                    o.put("cdLivroReserva", r.getCdLivroReserva());
                    o.put("cdAlugadorReserva", r.getCdAlugadorReserva());
                    o.put("devolucao", r.getDevolucao());
                    arr.put(o);
                }
                file.put("Reservas", arr);
           }
       }catch(Exception ex){
           response.setStatus(500);
           file.put("error", ex.getLocalizedMessage());
       }
       response.getWriter().print(file.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset = utf-8");
        JSONObject file = new JSONObject();
       try{
            JSONObject body = getJSONBody(request.getReader());
            //pegando valores
            Integer cdLivroReserva = body.getInt("cdLivroReserva");
            Integer cdAlugadorReserva = body.getInt("cdAlugadorReserva");
            LocalDate devolucao = LocalDate.now();
            devolucao = devolucao.plusDays(20);
            Date dataDevolucao = Date.from(devolucao.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            Livro l = Livro.getLivro(cdLivroReserva);
            if(l.getDisponibilidade() <= 0) {
                file.put("Mensagem", "Não há mais unidades desse livro disponiveis");
            }
            else {
                if(cdLivroReserva != 0 && cdAlugadorReserva != 0 && dataDevolucao != null){
                    Reserva.insertReserva(cdLivroReserva, cdAlugadorReserva, dataDevolucao);
                    Livro.mudarDisponibilidadeLivro(cdLivroReserva, -1);
                }
                
                ArrayList<Reserva> list = Reserva.getReservas();
                JSONArray arr = new JSONArray();
                for(Reserva r : list) {
                    JSONObject o = new JSONObject();
                    o.put("cdReserva", r.getCdReserva());
                    o.put("cdLivroReserva", r.getCdLivroReserva());
                    o.put("cdAlugadorReserva", r.getCdAlugadorReserva());
                    o.put("devolucao", r.getDevolucao());
                    arr.put(o);
                }
                file.put("Reservas", arr);
            }
            
            
       }catch(Exception ex){
           response.setStatus(500);
           file.put("error", ex.getLocalizedMessage());
       }
       response.getWriter().print(file.toString());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json;charset = utf-8");
        JSONObject file = new JSONObject();
        try{
           int identificador = Integer.parseInt(request.getParameter("cdReserva"));
           
           Reserva res = Reserva.getReserva(identificador);
           
           Reserva.deleteReserva(identificador);
           Livro.mudarDisponibilidadeLivro(res.getCdLivroReserva(), 1);
           
           ArrayList<Reserva> list = Reserva.getReservas();
            JSONArray arr = new JSONArray();
            for(Reserva r : list) {
                JSONObject o = new JSONObject();
                o.put("cdReserva", r.getCdReserva());
                o.put("cdLivroReserva", r.getCdLivroReserva());
                o.put("cdAlugadorReserva", r.getCdAlugadorReserva());
                o.put("devolucao", r.getDevolucao());
                arr.put(o);
            }
            file.put("Reservas", arr);
        }catch(Exception ex){
           response.setStatus(500);
           file.put("error", ex.getLocalizedMessage());
        }
        response.getWriter().print(file.toString());
        
    }

}