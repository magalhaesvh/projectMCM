package projectmcm.model.domain;

import java.io.Serializable;

public class Status implements Serializable {
    private int idStatus;
    private String nome;
    private byte tipo;    

    public int getIdStatus() {
        return idStatus;
    }

    public String getNome() {
        return nome;
    }

    public byte getTipo() {
        return tipo;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(byte tipo) {
        this.tipo = tipo;
    }
    
}
