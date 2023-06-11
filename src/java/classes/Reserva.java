/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import api.AppListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reserva {
    public static ArrayList<Reserva> livrosReservados = new ArrayList<Reserva>();
    int cdLivroReserva, cdReserva, cdAlugadorReserva;
    Date devolucao;
    
    public static String getCreateStatement() {
        return "CREATE TABLE IF NOT EXISTS reserva("
                + "cdReserva INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "cdLivroReserva INTEGER,"
                + "cdAlugadorReserva INTEGER,"
                + "devolucao TEXT NOT NULL,"
                + "FOREIGN KEY (cdLivroReserva)"
                + "   REFERENCES livro (idLivro)"
                + "      ON DELETE CASCADE"
                + "      ON UPDATE NO ACTION,"
                + "FOREIGN KEY (cdAlugadorReserva)"
                + "   REFERENCES alugador (cdAlugador)"
                + "      ON DELETE CASCADE"
                + "      ON UPDATE NO ACTION"
                + ")";
    }
    
    public static ArrayList<Reserva> getReservas() throws Exception {
            ArrayList<Reserva> list = new ArrayList<>();
            Connection con = AppListener.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reserva");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            
            while(rs.next()) {
                int cdReserva = rs.getInt("cdReserva");
                int cdLivroReserva = rs.getInt("cdLivroReserva");
                int cdAlugadorReserva = rs.getInt("cdAlugadorReserva");
                Date devolucao = sdf.parse(rs.getString("devolucao"));
                list.add(new Reserva(cdLivroReserva, cdReserva, cdAlugadorReserva, devolucao));
            }
            rs.close();
            stmt.close();
            con.close();
            return list;
    }
    
    public static Reserva getReserva(int cdReserva) throws Exception {
            Reserva reserva = null;
            Connection con = AppListener.getConnection();
            String sql = "SELECT * from reserva WHERE cdReserva = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cdReserva);
            ResultSet rs = stmt.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            
            if(rs.next()) {
                int cd = rs.getInt("cdReserva");
                int cdLivroReserva = rs.getInt("cdLivroReserva");
                int cdAlugadorReserva = rs.getInt("cdAlugadorReserva");
                Date devolucao = sdf.parse(rs.getString("devolucao"));
                reserva = new Reserva(cdLivroReserva, cd, cdAlugadorReserva, devolucao);
            }
            rs.close();
            stmt.close();
            con.close();
            return reserva;
    }
    
    public static Reserva insertReserva(int cdLivroReserva, int cdAlugadorReserva, Date devolucao) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "INSERT INTO reserva (cdLivroReserva, cdAlugadorReserva, devolucao) VALUES (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            stmt.setInt(1, cdLivroReserva);
            stmt.setInt(2, cdAlugadorReserva);
            stmt.setString(3, sdf.format(devolucao));
            stmt.execute();
            stmt.close();
            con.close();
            Reserva reserva = new Reserva(cdLivroReserva, 0, cdAlugadorReserva, devolucao);
            return reserva;
    }
    
    public static Reserva updateReserva(int cdReserva, int cdLivroReserva, int cdAlugadorReserva, Date devolucao) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "UPDATE reserva SET cdLivroReserva=?, cdAlugadorReserva=?, devolucao=? WHERE cdReserva=?";
            PreparedStatement stmt = con.prepareStatement(sql);            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            stmt.setInt(1, cdLivroReserva);
            stmt.setInt(2, cdAlugadorReserva);
            stmt.setString(3, sdf.format(devolucao));
            stmt.setInt(4, cdReserva);
            stmt.execute();
            stmt.close();
            con.close();
            Reserva reserva = new Reserva(cdLivroReserva, cdReserva, cdAlugadorReserva, devolucao);
            return reserva;
    }
    
    public static void deleteReserva(int cdReserva) throws Exception {
            Connection con = AppListener.getConnection();
            String sql = "DELETE FROM reserva WHERE cdReserva=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cdReserva);
            stmt.execute();
            stmt.close();
            con.close();
    }

    public Reserva(int cdLivroReserva, int cdReserva, int cdAlugadorReserva, Date devolucao) {
        this.cdLivroReserva = cdLivroReserva;
        this.cdReserva = cdReserva;
        this.cdAlugadorReserva = cdAlugadorReserva;
        this.devolucao = devolucao;
        //aplicar 20 dias
    }

    public int getCdLivroReserva() {
        return cdLivroReserva;
    }

    public void setCdLivroReserva(int cdLivroReserva) {
        this.cdLivroReserva = cdLivroReserva;
    }

    public int getCdReserva() {
        return cdReserva;
    }

    public void setCdReserva(int cdReserva) {
        this.cdReserva = cdReserva;
    }

    public int getCdAlugadorReserva() {
        return cdAlugadorReserva;
    }

    public void setCdAlugadorReserva(int cdAlugadorReserva) {
        this.cdAlugadorReserva = cdAlugadorReserva;
    }

    public Date getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(Date devolucao) {
        this.devolucao = devolucao;
    }

    
    
}
