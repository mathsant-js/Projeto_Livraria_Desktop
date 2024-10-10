/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Matheus Santana
 */
public class Livro {
    private int codLivro;
    private String nomeLivro;
    private String isbnLivro;
    private Date dataLancamento;
    private float precoLivro;
    private String descricaoLivro;
    private Genero codGenero;
    private Editora codEditora;
    
    public Livro (int codLivro, String nomeLivro, String isbnLivro, Date dataLancamento,
            float precoLivro, String descricaoLivro, Genero codGenero, Editora codEditora
    ) 
    
    {
        this.codLivro = codLivro;
        this.nomeLivro = nomeLivro;
        this.isbnLivro = isbnLivro;
        this.dataLancamento = dataLancamento;
        this.precoLivro = precoLivro;
        this.descricaoLivro = descricaoLivro;
        this.codGenero = codGenero;
        this.codEditora = codEditora;
    }

    /**
     * @return the codLivro
     */
    public int getCodLivro() {
        return codLivro;
    }

    /**
     * @param codLivro the codLivro to set
     */
    public void setCodLivro(int codLivro) {
        this.codLivro = codLivro;
    }

    /**
     * @return the nomeLivro
     */
    public String getNomeLivro() {
        return nomeLivro;
    }

    /**
     * @param nomeLivro the nomeLivro to set
     */
    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    /**
     * @return the isbnLivro
     */
    public String getIsbnLivro() {
        return isbnLivro;
    }

    /**
     * @param isbnLivro the isbnLivro to set
     */
    public void setIsbnLivro(String isbnLivro) {
        this.isbnLivro = isbnLivro;
    }

    /**
     * @return the dataLancamento
     */
    public Date getDataLancamento() {
        return dataLancamento;
    }

    /**
     * @param dataLancamento the dataLancamento to set
     */
    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    /**
     * @return the precoLivro
     */
    public float getPrecoLivro() {
        return precoLivro;
    }

    /**
     * @param precoLivro the precoLivro to set
     */
    public void setPrecoLivro(float precoLivro) {
        this.precoLivro = precoLivro;
    }

    /**
     * @return the descricaoLivro
     */
    public String getDescricaoLivro() {
        return descricaoLivro;
    }

    /**
     * @param descricaoLivro the descricaoLivro to set
     */
    public void setDescricaoLivro(String descricaoLivro) {
        this.descricaoLivro = descricaoLivro;
    }

    /**
     * @return the codGenero
     */
    public Genero getCodGenero() {
        return codGenero;
    }

    /**
     * @param codGenero the codGenero to set
     */
    public void setCodGenero(Genero codGenero) {
        this.codGenero = codGenero;
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
    
    
}
