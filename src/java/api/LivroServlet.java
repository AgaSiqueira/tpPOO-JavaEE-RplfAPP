/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package api;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import org.json.JSONArray;
import org.json.JSONObject;
import classes.Livro;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author agatha
 */
@WebServlet(name = "LivroServlet", urlPatterns = {"/livro"})
@MultipartConfig
public class LivroServlet extends HttpServlet {

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
           if(request.getParameter("idLivro") != null) {
                int identificador = Integer.parseInt(request.getParameter("idLivro"));
                Livro l = Livro.getLivro(identificador);
                JSONObject o = new JSONObject();
                o.put("idLivro", l.getIdLivro());
                o.put("titulo", l.getTitulo());
                o.put("autor", l.getAutor());
                o.put("genero", l.getGenero());
                o.put("sinopse", l.getSinopse());
                o.put("editora", l.getEditora());
                o.put("isbn", l.getIsbn());
                o.put("idioma", l.getIdioma());
                o.put("disponibilidade", l.getDisponibilidade());
                o.put("quantidade", l.getQuantidade());
                o.put("ano", l.getAno());
                file.put("Livros", o);
           }
           else {
                ArrayList<Livro> list = Livro.getLivros();
                JSONArray arr = new JSONArray();
                for(Livro l : list) {
                    JSONObject o = new JSONObject();
                    o.put("idLivro", l.getIdLivro());
                    o.put("titulo", l.getTitulo());
                    o.put("autor", l.getAutor());
                    o.put("genero", l.getGenero());
                    o.put("sinopse", l.getSinopse());
                    o.put("editora", l.getEditora());
                    o.put("isbn", l.getIsbn());
                    o.put("idioma", l.getIdioma());
                    o.put("disponibilidade", l.getDisponibilidade());
                    o.put("quantidade", l.getQuantidade());
                    o.put("ano", l.getAno());
                    arr.put(o);
                }
                file.put("Livros", arr);
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
            String titulo = body.getString("titulo");
            String autor = body.getString("autor");
            String genero = body.getString("sinopse");
            String sinopse = body.getString("sinopse");
            String editora = body.getString("editora");
            String isbn = body.getString("isbn");
            String idioma = body.getString("idioma");
            Integer disponibilidade = body.getInt("disponibilidade");
            Integer quantidade = body.getInt("quantidade");
            Integer ano = body.getInt("ano");
           
            if(titulo != null && autor != null && genero != null && 
                    sinopse != null && editora != null && isbn != null && 
                    idioma != null && disponibilidade != 0 && quantidade != 0 && 
                    ano != 0){
                Livro.insertLivro(titulo, autor, genero, sinopse, editora, isbn, idioma, disponibilidade, quantidade, ano);
            }
           
            ArrayList<Livro> list = Livro.getLivros();
            JSONArray arr = new JSONArray();
            for(Livro l : list) {
                JSONObject o = new JSONObject();
                o.put("idLivro", l.getIdLivro());
                o.put("titulo", l.getTitulo());
                o.put("autor", l.getAutor());
                o.put("genero", l.getGenero());
                o.put("sinopse", l.getSinopse());
                o.put("editora", l.getEditora());
                o.put("isbn", l.getIsbn());
                o.put("idioma", l.getIdioma());
                o.put("disponibilidade", l.getDisponibilidade());
                o.put("quantidade", l.getQuantidade());
                o.put("ano", l.getAno());
                arr.put(o);
            }
            file.put("Livros", arr);
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
            int identificador = Integer.parseInt(request.getParameter("idLivro"));
            JSONObject body = getJSONBody(request.getReader());
            String titulo = body.getString("titulo");
            String autor = body.getString("autor");
            String genero = body.getString("sinopse");
            String sinopse = body.getString("sinopse");
            String editora = body.getString("editora");
            String isbn = body.getString("isbn");
            String idioma = body.getString("idioma");
            Integer disponibilidade = body.getInt("disponibilidade");
            Integer quantidade = body.getInt("quantidade");
            Integer ano = body.getInt("ano");
            Livro.updateLivro(identificador, titulo, autor, genero, sinopse, editora, isbn, idioma, disponibilidade, quantidade, ano);
            
            ArrayList<Livro> list = Livro.getLivros();
            JSONArray arr = new JSONArray();
            for(Livro l : list) {
                JSONObject o = new JSONObject();
                o.put("idLivro", l.getIdLivro());
                o.put("titulo", l.getTitulo());
                o.put("autor", l.getAutor());
                o.put("genero", l.getGenero());
                o.put("sinopse", l.getSinopse());
                o.put("editora", l.getEditora());
                o.put("isbn", l.getIsbn());
                o.put("idioma", l.getIdioma());
                o.put("disponibilidade", l.getDisponibilidade());
                o.put("quantidade", l.getQuantidade());
                o.put("ano", l.getAno());
                arr.put(o);
            }
            file.put("Livros", arr);
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
           int identificador = Integer.parseInt(request.getParameter("idLivro"));
           
           Livro.deleteLivro(identificador);
           
           ArrayList<Livro> list = Livro.getLivros();
            JSONArray arr = new JSONArray();
            for(Livro l : list) {
                JSONObject o = new JSONObject();
                o.put("idLivro", l.getIdLivro());
                o.put("titulo", l.getTitulo());
                o.put("autor", l.getAutor());
                o.put("genero", l.getGenero());
                o.put("sinopse", l.getSinopse());
                o.put("editora", l.getEditora());
                o.put("isbn", l.getIsbn());
                o.put("idioma", l.getIdioma());
                o.put("disponibilidade", l.getDisponibilidade());
                o.put("quantidade", l.getQuantidade());
                o.put("ano", l.getAno());
                arr.put(o);
            }
            file.put("Livros", arr);
        }catch(Exception ex){
           response.setStatus(500);
           file.put("error", ex.getLocalizedMessage());
        }
        response.getWriter().print(file.toString());
        
    }

}
