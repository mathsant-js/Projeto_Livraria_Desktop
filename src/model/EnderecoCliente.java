/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class EnderecoCliente {
    private int codCli;
    private String enderecoCliente;
    
    public EnderecoCliente (int codCli, String enderecoCliente) {
        this.codCli = codCli;
        this.enderecoCliente = enderecoCliente;
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
     * @return the enderecoCliente
     */
    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    /**
     * @param enderecoCliente the enderecoCliente to set
     */
    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }
    
    
}
