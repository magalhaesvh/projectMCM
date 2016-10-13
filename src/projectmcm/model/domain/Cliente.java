package projectmcm.model.domain;

import java.io.Serializable;
import java.sql.Date;

public class Cliente implements Serializable {
    private int idCliente;
    private String nome;
    private String cpf;
    private String rg;
    private String cnh;
    private Date dataNascimento;
    private Date dataVinculo;
    private String email;

       
    @Override
    public String toString() {
        return this.getNome();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getCnh() {
        return cnh;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Date getDataVinculo() {
        return dataVinculo;
    }

    public String getEmail() {
        return email;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDataVinculo(Date dataVinculo) {
        this.dataVinculo = dataVinculo;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
