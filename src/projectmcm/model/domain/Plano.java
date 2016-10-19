package projectmcm.model.domain;

import java.io.Serializable;

public class Plano implements Serializable {
    private int idPlano;
    private Promocao promocao;
    private String nome;
    private String regulamento;
    private String descricao;
    private int idTipo;
    private boolean calculoQuilometragem;
    private float valorQuilometragem;
    private boolean custoFixo;
    private float valorCusto;
    private boolean diaria;
    private float valorDiaria;

    public int getIdPlano() {
        return idPlano;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public String getNome() {
        return nome;
    }

    public String getRegulamento() {
        return regulamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public boolean isCalculoQuilometragem() {
        return calculoQuilometragem;
    }

    public float getValorQuilometragem() {
        return valorQuilometragem;
    }

    public boolean isCustoFixo() {
        return custoFixo;
    }

    public float getValorCusto() {
        return valorCusto;
    }

    public boolean isDiaria() {
        return diaria;
    }

    public float getValorDiaria() {
        return valorDiaria;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public void setIdPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRegulamento(String regulamento) {
        this.regulamento = regulamento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public void setCalculoQuilometragem(boolean calculoQuilometragem) {
        this.calculoQuilometragem = calculoQuilometragem;
    }

    public void setValorQuilometragem(float valorQuilometragem) {
        this.valorQuilometragem = valorQuilometragem;
    }

    public void setCustoFixo(boolean custoFixo) {
        this.custoFixo = custoFixo;
    }

    public void setValorCusto(float valorCusto) {
        this.valorCusto = valorCusto;
    }

    public void setDiaria(boolean diaria) {
        this.diaria = diaria;
    }

    public void setValorDiaria(float valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
