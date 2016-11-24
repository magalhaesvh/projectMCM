package projectmcm.model.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Locacao implements Serializable {
    private int idLocacao;
    private Cliente cliente;
    private Plano plano;
    private Funcionario locador;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private float kmInicial;
    private Veiculo veiculo;

    public int getIdLocacao() {
        return idLocacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Plano getPlano() {
        return plano;
    }

    public Funcionario getLocador() {
        return locador;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public float getKmInicial() {
        return kmInicial;
    }


    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public void setLocador(Funcionario locador) {
        this.locador = locador;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setKmInicial(float kmInicial) {
        this.kmInicial = kmInicial;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
