/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import api.AppListener;
import java.sql.*;
import java.util.ArrayList;


public class Livro {
    public static ArrayList<Livro> list = new ArrayList<>();
    String titulo,autor, genero,sinopse, editora, isbn, idioma;
    int idLivro,disponibilidade, quantidade, ano;
    
    public static String getCreateStatement() {
        return "CREATE TABLE IF NOT EXISTS livro("
                + "idLivro INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "titulo VARCHAR(50) NOT NULL,"
                + "autor VARCHAR(12) NOT NULL,"
                + "genero VARCHAR(50),"
                + "sinopse VARCHAR(20),"
                + "editora VARCHAR(20),"
                + "isbn VARCHAR(100),"
                + "idioma VARCHAR(100),"
                + "disponibilidade INTEGER,"
                + "quantidade INTEGER,"
                + "ano INTEGER"
                + ")";
    }

    public static ArrayList<Livro> getLivros() throws Exception {
            ArrayList<Livro> list = new ArrayList<>();
            Connection con = AppListener.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM livro");
            while(rs.next()) {
                int idLivro = rs.getInt("idLivro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                String sinopse = rs.getString("sinopse");
                String editora = rs.getString("editora");
                String isbn = rs.getString("isbn");
                String idioma = rs.getString("idioma");
                int disponibilidade = rs.getInt("disponibilidade");
                int quantidade = rs.getInt("quantidade");
                int ano = rs.getInt("ano");
                list.add(new Livro(titulo, autor, genero, sinopse, editora, isbn, idioma, idLivro, disponibilidade, quantidade, ano));
            }
            rs.close();
            stmt.close();
            con.close();
            return list;
    }
    
    public static Livro getLivro(int idLivro) throws Exception {
            Livro livro = null;
            Connection con = AppListener.getConnection();
            String sql = "SELECT * from livro WHERE idLivro = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idLivro);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("idLivro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                String sinopse = rs.getString("sinopse");
                String editora = rs.getString("editora");
                String isbn = rs.getString("isbn");
                String idioma = rs.getString("idioma");
                int disponibilidade = rs.getInt("disponibilidade");
                int quantidade = rs.getInt("quantidade");
                int ano = rs.getInt("ano");
                livro = new Livro(titulo, autor, genero, sinopse, editora, isbn, idioma, id, disponibilidade, quantidade, ano);
            }
            rs.close();
            stmt.close();
            con.close();
            return livro;
    }

    public static Livro insertLivro(String titulo, String autor, String genero, String sinopse, String editora, String isbn, String idioma, int disponibilidade, int quantidade, int ano) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "INSERT INTO livro (titulo, autor, genero, sinopse, editora, isbn, idioma, disponibilidade, quantidade, ano) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setString(3, genero);
            stmt.setString(4, sinopse);
            stmt.setString(5, editora);
            stmt.setString(6, isbn);
            stmt.setString(7, idioma);
            stmt.setInt(8, disponibilidade);
            stmt.setInt(9, quantidade);
            stmt.setInt(10, ano);
            stmt.execute();
            stmt.close();
            con.close();
            Livro livro = new Livro(titulo, autor, genero, sinopse, editora, isbn, idioma, 0, disponibilidade, quantidade, ano);
            return livro;
    }
    
    public static Livro updateLivro(int idLivro, String titulo, String autor, String genero, String sinopse, String editora, String isbn, String idioma, int disponibilidade, int quantidade, int ano) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "UPDATE livro SET titulo=?, autor=?, genero=?, sinopse=?, editora=?, isbn=?, idioma=?, disponibilidade=?, quantidade=?, ano=? WHERE idLivro=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setString(3, genero);
            stmt.setString(4, sinopse);
            stmt.setString(5, editora);
            stmt.setString(6, isbn);
            stmt.setString(7, idioma);
            stmt.setInt(8, disponibilidade);
            stmt.setInt(9, quantidade);
            stmt.setInt(10, ano);
            stmt.setInt(11, idLivro);
            stmt.execute();
            stmt.close();
            con.close();
            Livro livro = new Livro(titulo, autor, genero, sinopse, editora, isbn, idioma, idLivro, disponibilidade, quantidade, ano);
            return livro;
    }
    
    public static void mudarDisponibilidadeLivro(int idLivro, int mudanca) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "UPDATE livro SET disponibilidade=? WHERE idLivro=?";
            
            Livro l = getLivro(idLivro);
            int disponibilidade = l.getDisponibilidade() + mudanca;
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, disponibilidade);
            stmt.setInt(2, idLivro);
            stmt.execute();
            stmt.close();
            con.close();
    }
    
    public static void deleteLivro(int idLivro) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "DELETE FROM livro WHERE idLivro=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idLivro);
            stmt.execute();
            stmt.close();
            con.close();
    }
    
    public Livro(String titulo, String autor, String genero, String sinopse, String editora, String isbn, String idioma, int idLivro, int disponibilidade, int quantidade, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.sinopse = sinopse;
        this.editora = editora;
        this.isbn = isbn;
        this.idioma = idioma;
        this.disponibilidade = disponibilidade;
        this.quantidade = quantidade;
        this.ano = ano;
        this.idLivro = idLivro;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

}
