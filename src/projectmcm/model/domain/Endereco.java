package projectmcm.model.domain;

import java.io.Serializable;

public class Endereco implements Serializable {
    private int idEndereco;
    private Cidade cidade;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private Cliente cliente;
    private Agencia agencia;

    public int getIdEndereco() {
        return idEndereco;
    }

    public Cidade getCidade() {
        return cidade;
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

    public Cliente getCliente() {
        return cliente;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
}
