/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import api.AppListener;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Fatec
 */
public class User {
    //atributos diretamente banco de dados
    String nomeUser,senhaUser;
    
    public static String getCreateStatement() {
        return "CREATE TABLE IF NOT EXISTS user("
                + "nomeUser VARCHAR(50) PRIMARY KEY,"
                + "senhaUser VARCHAR(50) NOT NULL"
                + ")";
    }
    
    public static ArrayList<User> getUsers() throws Exception {
            ArrayList<User> list = new ArrayList<>();
            Connection con = AppListener.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while(rs.next()) {
                String nomeUser = rs.getString("nomeUser");
                String senhaUser = rs.getString("senhaUser");
                list.add(new User(nomeUser, senhaUser));
            }
            rs.close();
            stmt.close();
            con.close();
            return list;
    }
    
    public static User getUser(String nomeUser, String senhaUser) throws Exception {
            User user = null;
            Connection con = AppListener.getConnection();
            String sql = "SELECT * from user WHERE nomeUser = ? AND senhaUser = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nomeUser);
            stmt.setString(2, senhaUser);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                String nome = rs.getString("nomeUser");
                String senha = rs.getString("senhaUser");
                user = new User(nomeUser, senhaUser);
            }
            rs.close();
            stmt.close();
            con.close();
            return user;
    }
    
    public static User insertUser(String nomeUser, String senhaUser) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "INSERT INTO user (nomeUser, senhaUser) VALUES (?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nomeUser);
            stmt.setString(2, senhaUser);
            stmt.execute();
            stmt.close();
            con.close();
            User user = new User(nomeUser, senhaUser);
            return user;
    }
    
    public static User updateUser(String nomeUser, String senhaUser) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "UPDATE user SET senhaUser=? WHERE nomeUser=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, senhaUser);
            stmt.setString(2, nomeUser);
            stmt.execute();
            stmt.close();
            con.close();
            User user = new User(nomeUser, senhaUser);
            return user;
    }
    
    public static void deleteUser(String nomeUser) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "DELETE FROM user WHERE nomeUser=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nomeUser);
            stmt.execute();
            stmt.close();
            con.close();
    }

    public User(String nomeUser, String senhaUser) {
        this.nomeUser = nomeUser;
        this.senhaUser = senhaUser;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getSenhaUser() {
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser;
    }
    
}
