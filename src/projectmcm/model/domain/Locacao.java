package projectmcm.model.domain;

import java.io.Serializable;
import java.sql.Date;

public class Locacao implements Serializable {
    private int idLocacao;
    private int idCliente;
    private int idPlano;
    private int idLocador;
    private Date dataInicio;
    private Date dataFinal;
    private float kmInicial;
    private float kmFinal;
    private int idStatus;
    private int idAgenciaDevolucao;

    public int getIdLocacao() {
        return idLocacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public int getIdLocador() {
        return idLocador;
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

    public int getIdStatus() {
        return idStatus;
    }

    public int getIdAgenciaDevolucao() {
        return idAgenciaDevolucao;
    }

    public void setIdLocacao(int id_locacao) {
        this.idLocacao = id_locacao;
    }

    public void setIdCliente(int id_cliente) {
        this.idCliente = id_cliente;
    }

    public void setIPlano(int id_plano) {
        this.idPlano = id_plano;
    }

    public void setIdLocador(int id_locador) {
        this.idLocador = id_locador;
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

    public void setIdStatus(int id_status) {
        this.idStatus = id_status;
    }

    public void setIdAgenciaDevolucao(int id_agencia_devolucao) {
        this.idAgenciaDevolucao = id_agencia_devolucao;
    }

    
}
