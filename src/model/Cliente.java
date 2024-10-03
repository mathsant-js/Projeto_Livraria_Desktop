/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;

/**
 *
 * @author Admin
 */
public class Cliente {
    private int codCli;
    private String nomeCliente;
    private String clienteCpf;
    private Date dtNascCliente;
    private String senhaCliente;
    
    public Cliente (Integer codCli, String nomeCliente, String clienteCpf, Date dtNascCliente, String senhaCliente) {
        this.codCli = codCli;
        this.nomeCliente = nomeCliente;
        this.clienteCpf = clienteCpf;
        this.dtNascCliente = dtNascCliente;
        this.senhaCliente = senhaCliente;
    }

    public Cliente(int aInt, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * @return the codCli
     */
    public int getCodCli() {
        return codCli;
    }

    /**
     * @param codCli the codCli to set
     */
    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }

    /**
     * @return the nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @param nomeCliente the nomeCliente to set
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * @return the clienteCpf
     */
    public String getClienteCpf() {
        return clienteCpf;
    }

    /**
     * @param clienteCpf the clienteCpf to set
     */
    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    /**
     * @return the dtNascCliente
     */
    public Date getDtNascCliente() {
        return dtNascCliente;
    }

    /**
     * @param dtNascCliente the dtNascCliente to set
     */
    public void setDtNascCliente(Date dtNascCliente) {
        this.dtNascCliente = dtNascCliente;
    }

    /**
     * @return the senhaCliente
     */
    public String getSenhaCliente() {
        return senhaCliente;
    }

    /**
     * @param senhaCliente the senhaCliente to set
     */
    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }
}
