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
    private Editora codEditora;
    private String telefoneEditora;
    
    public TelefoneEditora (Editora codEditora, String telefoneEditora) {
        this.codEditora = codEditora;
        this.telefoneEditora = telefoneEditora;
    }

    /**
     * @return the codEditora
     */
    public Editora getCodEditora() {
        return codEditora;
    }

    /**
     * @param codEditora the codEditora to set
     */
    public void setCodEditora(Editora codEditora) {
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
