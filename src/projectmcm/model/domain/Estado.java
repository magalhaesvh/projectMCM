package projectmcm.model.domain;

import java.io.Serializable;

public class Estado implements Serializable {
    private int idEstado;
    private String nome;
    private String sigla;

    public int getIdEstado() {
        return idEstado;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
