package projectmcm.model.domain;

import java.io.Serializable;
import java.sql.Date;

public class Locacao implements Serializable {
    private int idLocacao;
    private Cliente cliente;
    private Plano plano;
    private Funcionario locador;
    private Date dataInicio;
    private Date dataFinal;
    private float kmInicial;
    private float kmFinal;
    private Agencia agenciaDevolucao;

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

    public Agencia getAgenciaDevolucao() {
        return agenciaDevolucao;
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

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setKmInicial(float kmInicial) {
        this.kmInicial = kmInicial;
    }

    public void setKmFinal(float kmFinal) {
        this.kmFinal = kmFinal;
    }

    public void setAgenciaDevolucao(Agencia agenciaDevolucao) {
        this.agenciaDevolucao = agenciaDevolucao;
    }
}
