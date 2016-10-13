package projectmcm.model.domain;

import java.io.Serializable;
import java.sql.Date;

public class Vistoria implements Serializable {
    private int idVistoria;
    private int idVeiculo;
    private int idFuncionario;
    private int idStatus;

    public int getIdVistoria() {
        return idVistoria;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdVistoria(int idVistoria) {
        this.idVistoria = idVistoria;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
}

    