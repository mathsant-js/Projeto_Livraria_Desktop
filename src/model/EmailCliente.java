/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class EmailCliente {
    private Cliente codCli;
    private String emailCliente;
    
    public EmailCliente (Cliente codCli, String emailCliente) {
        this.codCli = codCli;
        this.emailCliente = emailCliente;
    }

    /**
     * @return the codCli
     */
    public Cliente getCodCli() {
        return codCli;
    }

    /**
     * @param codCli the codCli to set
     */
    public void setCodCli(Cliente codCli) {
        this.codCli = codCli;
    }

    /**
     * @return the emailCliente
     */
    public String getEmailCliente() {
        return emailCliente;
    }

    /**
     * @param emailCliente the emailCliente to set
     */
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    
    
}
