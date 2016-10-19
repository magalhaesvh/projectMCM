package projectmcm.model.domain;

import java.io.Serializable;
import java.sql.Date;

public class Locacao implements Serializable {
    private Locacao locacao;
    private Cliente cliente;
    private Plano plano;
    private Funcionario locador;
    private Date dataInicio;
    private Date dataFinal;
    private float kmInicial;
    private float kmFinal;
    private Status status;
    private Agencia agenciaDevolucao;

    public Locacao getLocacao() {
        return locacao;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public float getKmInicial() {
        return kmInicial;
    }

    public float getKmFinal() {
        return kmFinal;
    }

    public Status getStatus() {
        return status;
    }

    public Agencia getAgenciaDevolucao() {
        return agenciaDevolucao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
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

    public void setDataInicio(Date data_inicio) {
        this.dataInicio = data_inicio;
    }

    public void setDataFinal(Date data_final) {
        this.dataFinal = data_final;
    }

    public void setKmInicial(float km_inicial) {
        this.kmInicial = km_inicial;
    }

    public void setKmFinal(float km_final) {
        this.kmFinal = km_final;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAgenciaDevolucao(Agencia agencia_devolucao) {
        this.agenciaDevolucao = agencia_devolucao;
    }

    
}
