/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;

/**
 *
 * @author Fatec
 */
public class Alugador {
    public static ArrayList<Alugador> list = new ArrayList<>();
    String nomeAlugador, cpf, email, telefone, endereco;
    int cdAlugador;

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
