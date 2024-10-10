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
public class Lista {
    private int codLista;
    private Date dataCriacaoLista;
    private Cliente codCli;
    
    public Lista (int codLista, Date dataCriacaoLista, Cliente codCli) {
        this.codLista = codLista;
        this.dataCriacaoLista = dataCriacaoLista;
        this.codCli = codCli;
    }

    /**
     * @return the codLista
     */
    public int getCodLista() {
        return codLista;
    }

    /**
     * @param codLista the codLista to set
     */
    public void setCodLista(int codLista) {
        this.codLista = codLista;
    }

    /**
     * @return the dataCriacaoLista
     */
    public Date getDataCriacaoLista() {
        return dataCriacaoLista;
    }

    /**
     * @param dataCriacaoLista the dataCriacaoLista to set
     */
    public void setDataCriacaoLista(Date dataCriacaoLista) {
        this.dataCriacaoLista = dataCriacaoLista;
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
    
}
