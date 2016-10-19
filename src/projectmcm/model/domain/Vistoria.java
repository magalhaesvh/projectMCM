package projectmcm.model.domain;

import java.io.Serializable;

public class Vistoria implements Serializable {
    private int idVistoria;
    private Veiculo veiculo;
    private Funcionario funcionario;
    private Status status;
    private String observacoes;

    public int getIdVistoria() {
        return idVistoria;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Status getStatus() {
        return status;
    }

    public void setIdVistoria(int idVistoria) {
        this.idVistoria = idVistoria;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}

    