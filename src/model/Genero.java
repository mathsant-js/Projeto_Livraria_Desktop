/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Matheus Santana
 */
public class Genero {
    private int codGenero;
    private String nomeGenero;
    private String descricaoGenero;
    
    public Genero (int codGenero, String nomeGenero, String descricaoGenero) {
        this.codGenero = codGenero;
        this.nomeGenero = nomeGenero;
        this.descricaoGenero = descricaoGenero;
    }
    
    public Genero (String nomeGenero, String descricaoGenero) {
        this.nomeGenero = nomeGenero;
        this.descricaoGenero = descricaoGenero;
    }

    /**
     * @return the codGenero
     */
    public int getCodGenero() {
        return codGenero;
    }

    /**
     * @param codGenero the codGenero to set
     */
    public void setCodGenero(int codGenero) {
        this.codGenero = codGenero;
    }

    /**
     * @return the nomeGenero
     */
    public String getNomeGenero() {
        return nomeGenero;
    }

    /**
     * @param nomeGenero the nomeGenero to set
     */
    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

    /**
     * @return the descricaoGenero
     */
    public String getDescricaoGenero() {
        return descricaoGenero;
    }

    /**
     * @param descricaoGenero the descricaoGenero to set
     */
    public void setDescricaoGenero(String descricaoGenero) {
        this.descricaoGenero = descricaoGenero;
    }
    
    
}
