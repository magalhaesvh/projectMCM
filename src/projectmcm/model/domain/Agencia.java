package projectmcm.model.domain;

import java.io.Serializable;

public class Agencia implements Serializable {
    private int idAgencia;
    private String nome;
    private String cnpj;
    private String telefone;
    private Endereco endereco;

       
    @Override
    public String toString() {
        return this.getNome();
    }    

    public int getIdAgencia() {
        return idAgencia;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setIdAgencia(int id_agencia) {
        this.idAgencia = id_agencia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
