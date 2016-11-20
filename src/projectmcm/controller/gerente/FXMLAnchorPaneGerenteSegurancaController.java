/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller.gerente;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import projectmcm.model.domain.Funcionario;

/**
 * FXML Controller class
 *
 * @author vh_ma
 */
public class FXMLAnchorPaneGerenteSegurancaController implements Initializable {

    private Funcionario logado;
    @FXML
    private Label labelSegurancaNome;
    @FXML
    private Label labelSegurancaEmail;
    @FXML
    private Label labelSegurancaSenha;
    @FXML
    private Label labelSegurancaCpf;
    @FXML
    private Label labelSegurancaRg;
    @FXML
    private TextField textFieldSegurancaNome;
    @FXML
    private TextField textFieldSegurancaEmail;
    @FXML
    private TextField textFieldSegurancaSenha;
    @FXML
    private TextField textFieldSegurancaCpf;
    @FXML
    private TextField textFieldSegurancaRg;
    @FXML
    private PasswordField passwordFieldSegurancaSenhaAtual;
    @FXML
    private PasswordField passwordFieldSegurancaNovaSenha;
    @FXML
    private PasswordField passwordFieldSegurancaConfirmarSenha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void exibirDados() {
        if (this.logado != null) {
            this.labelSegurancaNome.setText(logado.getNome());
            this.labelSegurancaEmail.setText(logado.getEmail());
            this.labelSegurancaCpf.setText(logado.getCpf());
            this.labelSegurancaRg.setText(logado.getRg());
            this.labelSegurancaSenha.setText(logado.getSenha());
        } else {
            this.labelSegurancaNome.setText("");
            this.labelSegurancaEmail.setText("");
            this.labelSegurancaCpf.setText("");
            this.labelSegurancaRg.setText("");
            this.labelSegurancaSenha.setText("");
        }
    }

    public void handleButtonSalvarDados() {
        if (this.validarDados()) {
            this.logado.setNome(this.textFieldSegurancaNome.getText());
            this.logado.setCpf(this.textFieldSegurancaCpf.getText());
            this.logado.setEmail(this.textFieldSegurancaEmail.getText());
            this.logado.setRg(this.textFieldSegurancaRg.getText());
        }else{
            System.out.println("Erro nos dados!");
        }
    }

    public boolean validarDados() {
        boolean valida = false;
        if (this.textFieldSegurancaNome.getText().length() == 0 || this.textFieldSegurancaNome.getText() == null) {
            System.out.println("Entrada vazia");
        } else if (this.textFieldSegurancaCpf.getText().length() == 0 || this.textFieldSegurancaCpf.getText().length() < 11 || this.textFieldSegurancaCpf.getText() == null) {
            System.out.println("CPF errado!");
        } else {
            valida = true;
        }
        return valida;
    }

    public Funcionario getLogado() {
        return logado;
    }

    public void setLogado(Funcionario logado) {
        this.logado = logado;
    }

}
