package projectmcm.model.domain;

import java.io.Serializable;
import java.sql.Date;

public class Promocao implements Serializable {
    private int idPromocao;
    private Date dataInicio;
    private Date dataTermino;
    private int quantidade;

    public int getIdPromocao() {
        return idPromocao;
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