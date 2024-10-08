/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class TelefoneCliente {
    private Cliente codCli;
    private String telefoneCliente;
    
    public TelefoneCliente (Cliente codCli, String telefoneCliente) {
        this.codCli = codCli;
        this.telefoneCliente = telefoneCliente;
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
     * @return the telefoneCliente
     */
    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    /**
     * @param telefoneCliente the telefoneCliente to set
     */
    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }
    
    
}
