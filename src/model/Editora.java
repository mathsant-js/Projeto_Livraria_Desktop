/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Matheus Santana
 */
public class Editora {
    private int codEditora;
    private String nomeEditora;
    private String enderecoEditora;
    
    public Editora (int codEditora, String nomeEditora, String enderecoEditora) {
        this.codEditora = codEditora;
        this.nomeEditora = nomeEditora;
        this.enderecoEditora = enderecoEditora;
    }
    
    public Editora (String nomeEditora, String enderecoEditora) {
        this.nomeEditora = nomeEditora;
        this.enderecoEditora = enderecoEditora;
    }

    /**
     * @return the codEditora
     */
    public int getCodEditora() {
        return codEditora;
    }

    /**
     * @param codEditora the codEditora to set
     */
    public void setCodEditora(int codEditora) {
        this.codEditora = codEditora;
    }

    /**
     * @return the nomeEditora
     */
    public String getNomeEditora() {
        return nomeEditora;
    }

    /**
     * @param nomeEditora the nomeEditora to set
     */
    public void setNomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
    }

    /**
     * @return the enderecoEditora
     */
    public String getEnderecoEditora() {
        return enderecoEditora;
    }

    /**
     * @param enderecoEditora the enderecoEditora to set
     */
    public void setEnderecoEditora(String enderecoEditora) {
        this.enderecoEditora = enderecoEditora;
    }
    
    
}
