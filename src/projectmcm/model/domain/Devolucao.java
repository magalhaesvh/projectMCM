/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.model.domain;

import java.util.Date;

/**
 *
 * @author vh_ma
 */
public class Devolucao {
    private int id;
    private Locacao locacao;
    private Date dataDevolucao;
    private float kmFinal;
    
    public Devolucao(){
        this.id = -1;
        this.locacao = new Locacao();
        this.dataDevolucao = null;
        this.kmFinal = 0;
    }

    public int getId() {
        return id;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public float getKmFinal() {
        return kmFinal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setKmFinal(float kmFinal) {
        this.kmFinal = kmFinal;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }
}
