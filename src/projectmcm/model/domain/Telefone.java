package projectmcm.model.domain;

import java.io.Serializable;

public class Telefone implements Serializable {
    private int idTelefone;
    private int idCliente;
    private int idAgencia;
    private String telefone;
    private int idStatus;

    public int getIdTelefone() {
        return idTelefone;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdSgencia() {
        return idAgencia;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setId_agencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    
}
