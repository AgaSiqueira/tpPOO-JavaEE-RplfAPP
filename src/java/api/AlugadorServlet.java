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
           file.put("Alugadores", new JSONArray(Alugador.list));
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
           int cdAlugador = random.nextInt(10000);
           String nomeAlugador = body.getString("nomeAlugador");
           String cpf = body.getString("cpf");
           String email = body.getString("email");
           String telefone = body.getString("telefone");
           String endereco = body.getString("endereco");
           
           if(nomeAlugador != null && cpf != null && email != null && 
                   telefone != null && endereco != null){
               Alugador alugar = new Alugador(nomeAlugador,cpf,email,
                       telefone,endereco,cdAlugador);
               Alugador.list.add(alugar);
           }
           file.put("Alugadores", new JSONArray(Alugador.list));
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
            Alugador alugadorAtualizado = null;
            for (Alugador alugar : Alugador.list) {
                if (alugar.getCdAlugador() == identificador) {
                    alugadorAtualizado = alugar;
                    break;
                }
            }
            if (alugadorAtualizado != null) {
                JSONObject body = getJSONBody(request.getReader());
                if (body.has("nomeAlugador")) {
                    alugadorAtualizado.setNomeAlugador(body.getString("nomeAlugador"));
                }
                if (body.has("cpf")) {
                    alugadorAtualizado.setCpf(body.getString("cpf"));
                }
                if (body.has("email")) {
                    alugadorAtualizado.setEmail(body.getString("email"));
                }
                if (body.has("telefone")) {
                    alugadorAtualizado.setTelefone(body.getString("telefone"));
                }
                if (body.has("endereco")) {
                    alugadorAtualizado.setEndereco(body.getString("endereco"));
                }
                
                int index = Alugador.list.indexOf(alugadorAtualizado);
                Alugador.list.set(index, alugadorAtualizado);
                file.put("Alugadores", new JSONArray(Alugador.list));
            } else {
                response.setStatus(404);
                file.put("error", "Alugador não encontrado");
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
           int indentificador = Integer.parseInt(request.getParameter("cdAlugador"));
           int alugadorRetirado = -1;
           for(Alugador a: Alugador.list){
               if(a.getCdAlugador() == indentificador){
                alugadorRetirado= Alugador.list.indexOf(a);
                break;
               }
           }
           if (alugadorRetirado>-1) Alugador.list.remove(alugadorRetirado);
           file.put("Alugadores", new JSONArray(Alugador.list));
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
