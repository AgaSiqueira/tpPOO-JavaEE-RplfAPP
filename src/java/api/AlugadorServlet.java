/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package api;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import org.json.JSONArray;
import org.json.JSONObject;
import classes.Alugador;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author agath
 */
@WebServlet(name = "AlugadorServlet", urlPatterns = {"/alugador"})
public class AlugadorServlet extends HttpServlet {

    private JSONObject getJSONBody(BufferedReader reader) throws Exception{
         StringBuilder buffer = new StringBuilder();
         String line = null;
         while((line = reader.readLine()) != null){
             buffer.append(line);
         }
         return new JSONObject(buffer.toString());
     }
    
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
           if(request.getParameter("cdAlugador") != null) {
                int identificador = Integer.parseInt(request.getParameter("cdAlugador"));
                Alugador a = Alugador.getAlugador(identificador);
                JSONObject o = new JSONObject();
                o.put("cdAlugador", a.getCdAlugador());
                o.put("nomeAlugador", a.getNomeAlugador());
                o.put("cpf", a.getCpf());
                o.put("email", a.getEmail());
                o.put("telefone", a.getTelefone());
                o.put("endereco", a.getEndereco());
                file.put("Alugadores", o);
           }
           else {
                ArrayList<Alugador> list = Alugador.getAlugadores();
                JSONArray arr = new JSONArray();
                for(Alugador a : list) {
                    JSONObject o = new JSONObject();
                    o.put("cdAlugador", a.getCdAlugador());
                    o.put("nomeAlugador", a.getNomeAlugador());
                    o.put("cpf", a.getCpf());
                    o.put("email", a.getEmail());
                    o.put("telefone", a.getTelefone());
                    o.put("endereco", a.getEndereco());
                    arr.put(o);
                }
                file.put("Alugadores", arr);
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
           String nomeAlugador = body.getString("nomeAlugador");
           String cpf = body.getString("cpf");
           String email = body.getString("email");
           String telefone = body.getString("telefone");
           String endereco = body.getString("endereco");
           
           if(nomeAlugador != null && cpf != null && email != null && 
                   telefone != null && endereco != null){
               Alugador.insertAlugador(nomeAlugador,cpf,email,
                       telefone,endereco);
           }
           ArrayList<Alugador> list = Alugador.getAlugadores();
           JSONArray arr = new JSONArray();
           for(Alugador a : list) {
               JSONObject o = new JSONObject();
               o.put("cdAlugador", a.getCdAlugador());
               o.put("nomeAlugador", a.getNomeAlugador());
               o.put("cpf", a.getCpf());
               o.put("email", a.getEmail());
               o.put("telefone", a.getTelefone());
               o.put("endereco", a.getEndereco());
               arr.put(o);
           }
           file.put("Alugadores", arr);
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
            int identificador = Integer.parseInt(request.getParameter("cdAlugador"));
            JSONObject body = getJSONBody(request.getReader());
            String nomeAlugador = body.getString("nomeAlugador");
            String cpf = body.getString("cpf");
            String email = body.getString("email");
            String telefone = body.getString("telefone");
            String endereco = body.getString("endereco");
            Alugador.updateAlugador(identificador, nomeAlugador, cpf, email, telefone, endereco);
            
            ArrayList<Alugador> list = Alugador.getAlugadores();
           JSONArray arr = new JSONArray();
           for(Alugador a : list) {
               JSONObject o = new JSONObject();
               o.put("cdAlugador", a.getCdAlugador());
               o.put("nomeAlugador", a.getNomeAlugador());
               o.put("cpf", a.getCpf());
               o.put("email", a.getEmail());
               o.put("telefone", a.getTelefone());
               o.put("endereco", a.getEndereco());
               arr.put(o);
           }
           file.put("Alugadores", arr);
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
           int identificador = Integer.parseInt(request.getParameter("cdAlugador"));
           
           Alugador.deleteAlugador(identificador);
           
           ArrayList<Alugador> list = Alugador.getAlugadores();
           JSONArray arr = new JSONArray();
           for(Alugador a : list) {
               JSONObject o = new JSONObject();
               o.put("cdAlugador", a.getCdAlugador());
               o.put("nomeAlugador", a.getNomeAlugador());
               o.put("cpf", a.getCpf());
               o.put("email", a.getEmail());
               o.put("telefone", a.getTelefone());
               o.put("endereco", a.getEndereco());
               arr.put(o);
           }
           file.put("Alugadores", arr);
       }catch(Exception ex){
           response.setStatus(500);
           file.put("error", ex.getLocalizedMessage());
       }
       response.getWriter().print(file.toString());
        
    }

}
