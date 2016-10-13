package projectmcm.model.domain;

import java.io.Serializable;

public class Endereco implements Serializable {
    private int idEndereco;
    private int idCidade;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private int idCliente;
    private int idAgencia;

    public int getIdEndereco() {
        return idEndereco;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }
}
