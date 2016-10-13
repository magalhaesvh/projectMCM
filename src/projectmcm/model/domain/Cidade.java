package projectmcm.model.domain;

import java.io.Serializable;

public class Cidade implements Serializable {
    private int idCidade;
    private int idEstado;
    private String nome;

    public int getIdCidade() {
        return idCidade;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public String getNome() {
        return nome;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
