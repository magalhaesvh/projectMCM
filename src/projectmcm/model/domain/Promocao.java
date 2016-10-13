package projectmcm.model.domain;

import java.io.Serializable;
import java.sql.Date;

public class Promocao implements Serializable {
    private int idPromocao;
    private int nome;
    private Date dataInicio;
    private Date dataTermino;
    private int quantidade;

    public int getIdPromocao() {
        return idPromocao;
    }

    public int getNome() {
        return nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setIdPromocao(int idPromocao) {
        this.idPromocao = idPromocao;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}