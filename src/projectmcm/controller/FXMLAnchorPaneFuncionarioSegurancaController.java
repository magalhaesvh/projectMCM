/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller;

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
 * @author Monica
 */
public class FXMLAnchorPaneFuncionarioSegurancaController implements Initializable {

    @FXML
    private Label labelFuncionarioSegurancaCpf;
    @FXML
    private TextField textFieldFuncionarioSegurancaNome;
    @FXML
    private TextField textFieldFuncionarioSegurancaEmail;
    @FXML
    private TextField textFieldFuncionarioSegurancaCpf;
    @FXML
    private TextField textFieldFuncionarioSegurancaRg;
    @FXML
    private Label labelFuncionarioNome;
    @FXML
    private Label labelFuncionarioEmail;
    @FXML
    private Label labelFuncionarioCpf;
    @FXML
    private Label labelFuncionarioRg;
    @FXML
    private Label labelFuncionarioSegurancaNome;
    @FXML
    private Label labelFuncionarioSegurancaSenha;
    @FXML
    private Label labelFuncionarioSegurancaRg;
    @FXML
    private Label labelFuncionarioSegurancaEmail;
    @FXML
    private Label labelFuncionarioNome1;
    @FXML
    private Label labelFuncionarioEmail1;
    @FXML
    private Label labelFuncionarioCpf1;
    @FXML
    private Label labelFuncionarioRg1;
    @FXML
    private Label labelFuncionarioNome11;
    @FXML
    private Label labelFuncionarioEmail11;
    @FXML
    private Label labelFuncionarioCpf11;
    @FXML
    private PasswordField passwordFieldFuncionarioSegurancaSenhaAtual;
    @FXML
    private PasswordField passwordFieldFuncionarioSegurancaNovaSenha;
    @FXML
    private PasswordField passwordFieldFuncionarioSegurancaConfirmarSenha;

    private Funcionario funcionarioLogado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("123");
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
        labelFuncionarioSegurancaCpf.setText(funcionarioLogado.getCpf());
        textFieldFuncionarioSegurancaNome.setText(funcionarioLogado.getNome());
        textFieldFuncionarioSegurancaEmail.setText(funcionarioLogado.getEmail());
        textFieldFuncionarioSegurancaCpf.setText(funcionarioLogado.getCpf());
        textFieldFuncionarioSegurancaRg.setText(funcionarioLogado.getRg());
        labelFuncionarioNome.setText(funcionarioLogado.getNome());
        labelFuncionarioEmail.setText(funcionarioLogado.getEmail());
        labelFuncionarioCpf.setText(funcionarioLogado.getCpf());
        labelFuncionarioRg.setText(funcionarioLogado.getRg());
        labelFuncionarioSegurancaNome.setText(funcionarioLogado.getNome());
        labelFuncionarioSegurancaSenha.setText(funcionarioLogado.getSenha());
        labelFuncionarioSegurancaRg.setText(funcionarioLogado.getRg());
        labelFuncionarioSegurancaEmail.setText(funcionarioLogado.getEmail());
        labelFuncionarioNome1.setText(funcionarioLogado.getNome());
        labelFuncionarioEmail1.setText(funcionarioLogado.getEmail());
        labelFuncionarioCpf1.setText(funcionarioLogado.getCpf());
        labelFuncionarioRg1.setText(funcionarioLogado.getRg());
        labelFuncionarioNome11.setText(funcionarioLogado.getNome());
        labelFuncionarioEmail11.setText(funcionarioLogado.getEmail());
        labelFuncionarioCpf11.setText(funcionarioLogado.getCpf());
    }

}
