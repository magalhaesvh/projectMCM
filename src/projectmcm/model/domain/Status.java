package projectmcm.model.domain;

import java.io.Serializable;

public class Status implements Serializable {
    private int idStatus;
    private String nome;

    public int getIdStatus() {
        return idStatus;
    }

    public String getNome() {
        return nome;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }    
}
