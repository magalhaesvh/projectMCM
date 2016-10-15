package projectmcm.model.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Funcionario implements Serializable {
    private int idFuncionario;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String rg;
    private LocalDate dataContratacao;
    private byte tipo;
    private Agencia agencia;

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public byte getTipo() {
        return tipo;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public void setTipo(byte tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
    
    
}
