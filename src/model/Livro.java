/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;

/**
 *
 * @author lucas
 */
public class Livro {
    private int codLivro;
    private String nomeLivro;
    private String isbnLivro;
    private Date dataLancamentoLivro;
    private float precoLivro;
    private int codAutor;
    private int codEditora;
    private int codGenero;
    private String descricaoLivro;
    
    public Livro(int codLivro, String nomeLivro, String isbnLivro, Date dataLancamentoLivro, float precoLivro, int codAutor, int codEditora, int codGenero, String descricaoLivro) {
        this.codLivro = codLivro;
        this.nomeLivro = nomeLivro;
        this.isbnLivro = isbnLivro;
        this.dataLancamentoLivro = dataLancamentoLivro;
        this.precoLivro = precoLivro;
        this.codAutor = codAutor;
        this.codEditora = codEditora;
        this.codGenero = codGenero;
        this.descricaoLivro = descricaoLivro;
    }
    
    public Livro(String nomeLivro, String isbnLivro, Date dataLancamentoLivro, float precoLivro, int codAutor, int codEditora, int codGenero, String descricaoLivro) {
        this.nomeLivro = nomeLivro;
        this.isbnLivro = isbnLivro;
        this.dataLancamentoLivro = dataLancamentoLivro;
        this.precoLivro = precoLivro;
        this.codAutor = codAutor;
        this.codEditora = codEditora;
        this.codGenero = codGenero;
        this.descricaoLivro = descricaoLivro;
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
     * @return the dataLancamentoLivro
     */
    public Date getDataLancamentoLivro() {
        return dataLancamentoLivro;
    }

    /**
     * @param dataLancamentoLivro the dataLancamentoLivro to set
     */
    public void setDataLancamentoLivro(Date dataLancamentoLivro) {
        this.dataLancamentoLivro = dataLancamentoLivro;
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
     * @return the codAutor
     */
    public int getCodAutor() {
        return codAutor;
    }

    /**
     * @param codAutor the codAutor to set
     */
    public void setCodAutor(int codAutor) {
        this.codAutor = codAutor;
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
    
    
}
