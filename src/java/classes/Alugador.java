/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import api.AppListener;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Fatec
 */
public class Alugador {
    public static ArrayList<Alugador> list = new ArrayList<>();
    String nomeAlugador, cpf, email, telefone, endereco;
    int cdAlugador;
    
    public static String getCreateStatement() {
        return "CREATE TABLE IF NOT EXISTS alugador("
                + "cdAlugador INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nomeAlugador VARCHAR(50) NOT NULL,"
                + "cpf VARCHAR(12) NOT NULL,"
                + "email VARCHAR(50),"
                + "telefone VARCHAR(20),"
                + "endereco VARCHAR(100)"
                + ")";
    }
    
    public static ArrayList<Alugador> getAlugadores() throws Exception {
            ArrayList<Alugador> list = new ArrayList<>();
            Connection con = AppListener.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM alugador");
            while(rs.next()) {
                int cdAlugador = rs.getInt("cdAlugador");
                String nomeAlugador = rs.getString("nomeAlugador");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                list.add(new Alugador(nomeAlugador, cpf, email, telefone, endereco, cdAlugador));
            }
            rs.close();
            stmt.close();
            con.close();
            return list;
    }
    
    public static Alugador getAlugador(int cdAlugador) throws Exception {
            Alugador alugador = null;
            Connection con = AppListener.getConnection();
            String sql = "SELECT * from alugador WHERE cdAlugador = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cdAlugador);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("cdAlugador");
                String nomeAlugador = rs.getString("nomeAlugador");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                alugador = new Alugador(nomeAlugador, cpf, email, telefone, endereco, cdAlugador);
            }
            rs.close();
            stmt.close();
            con.close();
            return alugador;
    }
    
    public static Alugador insertAlugador(String nomeAlugador, String cpf, String email, String telefone, String endereco) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "INSERT INTO alugador (nomeAlugador, cpf, email, telefone, endereco) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nomeAlugador);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, endereco);
            stmt.execute();
            stmt.close();
            con.close();
            Alugador alugador = new Alugador(nomeAlugador, cpf, email, telefone, endereco, 0);
            return alugador;
    }
    
    public static Alugador updateAlugador(int cdAlugador, String nomeAlugador, String cpf, String email, String telefone, String endereco) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "UPDATE alugador SET nomeAlugador=?, cpf=?, email=?, telefone=?, endereco=? WHERE cdAlugador=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nomeAlugador);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, endereco);
            stmt.setInt(6, cdAlugador);
            stmt.execute();
            stmt.close();
            con.close();
            Alugador alugador = new Alugador(nomeAlugador, cpf, email, telefone, endereco, cdAlugador);
            return alugador;
    }
    
    public static void deleteAlugador(int cdAlugador) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "DELETE FROM alugador WHERE cdAlugador=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cdAlugador);
            stmt.execute();
            stmt.close();
            con.close();
    }

    public Alugador(String nomeAlugador, String cpf, String email, String telefone, String endereco, int cdAlugador) {
        this.nomeAlugador = nomeAlugador;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cdAlugador = cdAlugador;
    }

    public String getNomeAlugador() {
        return nomeAlugador;
    }

    public void setNomeAlugador(String nomeAlugador) {
        this.nomeAlugador = nomeAlugador;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCdAlugador() {
        return cdAlugador;
    }

    public void setCdAlugador(int cdAlugador) {
        this.cdAlugador = cdAlugador;
    }
    
    
}
