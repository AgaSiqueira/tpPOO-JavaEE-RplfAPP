/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package api;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import org.json.JSONArray;
import org.json.JSONObject;
import classes.Livro;
import java.util.Random;

/**
 *
 * @author agath
 */
@WebServlet(name = "LivroServlet", urlPatterns = {"/livro"})
public class LivroServlet extends HttpServlet {

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
           file.put("Livros", new JSONArray(Livro.list));
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
           //strings
           String titulo = body.getString("titulo");
           String autor = body.getString("autor");
           String genero = body.getString("genero");
           String sinopse = body.getString("sinopse");
           String editora = body.getString("editora");
           String isbn = body.getString("isbn");
           String idioma = body.getString("idioma");
           //int
           int  idLivro,disponibilidade=0, quantidade=0, ano=0;
           Random random = new Random();
           idLivro = random.nextInt(10000);
           disponibilidade = body.getInt("disponibilidade");
           quantidade = body.getInt("quantidade");
           ano = body.getInt("ano");
           
           if(titulo != null && autor != null && genero != null && 
                   sinopse != null && editora != null && isbn != null && 
                   idioma != null && disponibilidade != 0 && quantidade != 0 && 
                   ano != 0){
               Livro livros = new Livro(titulo,autor,genero,sinopse,editora,
                       isbn,idioma, idLivro, disponibilidade,quantidade,ano);
               Livro.list.add(livros);
           }
           file.put("Livros", new JSONArray(Livro.list));
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
           int identificador = Integer.parseInt(request.getParameter("livro"));
           int livroRetirado = -1;
           for(Livro l: Livro.list){
               if(l.getIdLivro()== identificador){
                livroRetirado= Livro.list.indexOf(l);
                break;
               }
           }
           if (livroRetirado>-1) Livro.list.remove(livroRetirado);
           file.put("Livros", new JSONArray(Livro.list));
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
