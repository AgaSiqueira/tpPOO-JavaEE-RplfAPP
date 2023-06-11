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
import classes.User;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author agath
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

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
            String nomeUser = request.getParameter("nomeUser");
            String senhaUser = request.getParameter("senhaUser");
            User u = User.getUser(nomeUser, senhaUser);
            if(u != null) {
                file.put("Resposta", "login concluido");
                file.put("Codigo", 200);
            }
            else {
                file.put("Resposta", "login ou senha incorretos");
                file.put("Codigo", 403);
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
           String nomeUser = body.getString("nomeUser");
           String senhaUser = body.getString("senhaUser");
           
           if(nomeUser != null && senhaUser != null){
               User.insertUser(nomeUser,senhaUser);
           }
           response.setStatus(200);
       }catch(Exception ex){
           response.setStatus(500);
           file.put("error", ex.getLocalizedMessage());
       }
       response.getWriter().print(file.toString());
    }

}
