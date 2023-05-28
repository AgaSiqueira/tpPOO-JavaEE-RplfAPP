/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fatec
 */
public class Reserva {
    ArrayList<Integer> livrosReservados = new ArrayList<Integer>();
    int cdLivroReserva, cdReserva, cdAlugadorReserva;
    Date devolucao;

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
