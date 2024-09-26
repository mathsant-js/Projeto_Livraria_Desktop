/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Cliente {
    private int codCli;
    private String nomeCliente;
    private String clienteCpf;
    private String dtNascCliente;
    
    public Cliente (Integer codCli, String nomeCliente, String clienteCpf, String dtNascCliente) {
        this.codCli = codCli;
        this.nomeCliente = nomeCliente;
        this.clienteCpf = clienteCpf;
        this.dtNascCliente = dtNascCliente;
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
    public String getDtNascCliente() {
        return dtNascCliente;
    }

    /**
     * @param dtNascCliente the dtNascCliente to set
     */
    public void setDtNascCliente(String dtNascCliente) {
        this.dtNascCliente = dtNascCliente;
    }
    
    
}
