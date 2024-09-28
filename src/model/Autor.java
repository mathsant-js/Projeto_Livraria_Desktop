/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Matheus Santana
 */
public class Autor {
    private int codAutor;
    private String nomeAutor;
    private String biografiaAutor;
    private String dataNascimentoAutor;
    private String dataFalecimentoAutor;
    private String nacionalidadeAutor;
    
    public Autor (
        int codAutor,
        String nomeAutor,
        String biografiaAutor, 
        String dataNascimentoAutor,
        String dataFalecimentoAutor,
        String nacionalidadeAutor
    ) {
        this.codAutor = codAutor;
        this.nomeAutor = nomeAutor;
        this.biografiaAutor = biografiaAutor;
        this.dataNascimentoAutor = dataNascimentoAutor;
        this.dataFalecimentoAutor = dataFalecimentoAutor;
        this.nacionalidadeAutor = nacionalidadeAutor;
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
     * @return the nomeAutor
     */
    public String getNomeAutor() {
        return nomeAutor;
    }

    /**
     * @param nomeAutor the nomeAutor to set
     */
    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    /**
     * @return the biografiaAutor
     */
    public String getBiografiaAutor() {
        return biografiaAutor;
    }

    /**
     * @param biografiaAutor the biografiaAutor to set
     */
    public void setBiografiaAutor(String biografiaAutor) {
        this.biografiaAutor = biografiaAutor;
    }

    /**
     * @return the dataNascimentoAutor
     */
    public String getDataNascimentoAutor() {
        return dataNascimentoAutor;
    }

    /**
     * @param dataNascimentoAutor the dataNascimentoAutor to set
     */
    public void setDataNascimentoAutor(String dataNascimentoAutor) {
        this.dataNascimentoAutor = dataNascimentoAutor;
    }

    /**
     * @return the dataFalecimentoAutor
     */
    public String getDataFalecimentoAutor() {
        return dataFalecimentoAutor;
    }

    /**
     * @param dataFalecimentoAutor the dataFalecimentoAutor to set
     */
    public void setDataFalecimentoAutor(String dataFalecimentoAutor) {
        this.dataFalecimentoAutor = dataFalecimentoAutor;
    }

    /**
     * @return the nacionalidadeAutor
     */
    public String getNacionalidadeAutor() {
        return nacionalidadeAutor;
    }

    /**
     * @param nacionalidadeAutor the nacionalidadeAutor to set
     */
    public void setNacionalidadeAutor(String nacionalidadeAutor) {
        this.nacionalidadeAutor = nacionalidadeAutor;
    }
}
