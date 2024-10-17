/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Matheus Santana
 */
public class TelefoneEditora {
    private int codEditora;
    private String telefoneEditora;
    
    public TelefoneEditora (int codEditora, String telefoneEditora) {
        this.codEditora = codEditora;
        this.telefoneEditora = telefoneEditora;
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
     * @return the telefoneEditora
     */
    public String getTelefoneEditora() {
        return telefoneEditora;
    }

    /**
     * @param telefoneEditora the telefoneEditora to set
     */
    public void setTelefoneEditora(String telefoneEditora) {
        this.telefoneEditora = telefoneEditora;
    }
    
}
