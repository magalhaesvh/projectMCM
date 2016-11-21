package projectmcm.model.domain;

import java.io.Serializable;
import java.sql.Date;

public class Veiculo implements Serializable {

    private int idVeiculo;
    private String modelo;
    private String marca;
    private String cor;
    private float valor;
    private String placa;
    private String chassi;
    private int anoModelo;
    private int anoFabricacao;
    private Date dataCompra;
    private String observacoes;
    private Status status;
    private boolean arCondicionado;
    private boolean vidroEletrico;
    private boolean travaEletrica;
    private boolean direcaoEletrica;
    private boolean cambioAutomatico;
    private float motor;
    private boolean abs;
    private boolean airBag;
    private boolean tracao4x4;
    private Agencia agencia;
    
    public Veiculo(){
        this.idVeiculo = -1;
        this.cor = "";
        this.valor = 0.0f;
        this.placa = "";
        this.chassi = "";
        this.anoModelo = -1;
        this.anoFabricacao = -1;
        this.dataCompra = null;
        this.observacoes = "";
        this.status = null;
        this.arCondicionado = false;
        this.vidroEletrico = false;
        this.travaEletrica = false;
        this.direcaoEletrica = false;
        this.cambioAutomatico = false;
        this.motor = 0.0f;
        this.abs = false;
        this.airBag = false;
        this.tracao4x4 = false;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getCor() {
        return cor;
    }

    public float getValor() {
        return valor;
    }

    public String getPlaca() {
        return placa;
    }

    public String getChassi() {
        return chassi;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public boolean isVidroEletrico() {
        return vidroEletrico;
    }

    public boolean isTravaEletrica() {
        return travaEletrica;
    }

    public boolean isDirecaoEletrica() {
        return direcaoEletrica;
    }

    public boolean isCambioAutomatico() {
        return cambioAutomatico;
    }

    public float getMotor() {
        return motor;
    }

    public boolean isAbs() {
        return abs;
    }

    public boolean isAirBag() {
        return airBag;
    }

    public boolean isTracao4x4() {
        return tracao4x4;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public void setVidroEletrico(boolean vidroEletrico) {
        this.vidroEletrico = vidroEletrico;
    }

    public void setTravaEletrica(boolean travaEletrica) {
        this.travaEletrica = travaEletrica;
    }

    public void setDirecaoEletrica(boolean direcaoEletrica) {
        this.direcaoEletrica = direcaoEletrica;
    }

    public void setCambioAutomatico(boolean cambioAutomatico) {
        this.cambioAutomatico = cambioAutomatico;
    }

    public void setMotor(float motor) {
        this.motor = motor;
    }

    public void setAbs(boolean abs) {
        this.abs = abs;
    }

    public void setAirBag(boolean airBag) {
        this.airBag = airBag;
    }

    public void setTracao4x4(boolean tracao4x4) {
        this.tracao4x4 = tracao4x4;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
    
   
}
