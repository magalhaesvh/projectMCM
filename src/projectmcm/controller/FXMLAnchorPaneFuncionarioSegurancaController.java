/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller;

import java.net.URL;
import java.util.ResourceBundle;
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

    private Label labelFuncionarioSegurancaCpf;
    private TextField textFieldFuncionarioSegurancaNome;
    private TextField textFieldFuncionarioSegurancaEmail;
    private TextField textFieldFuncionarioSegurancaCpf;
    private TextField textFieldFuncionarioSegurancaRg;
    private Label labelFuncionarioNome;
    private Label labelFuncionarioEmail;
    private Label labelFuncionarioCpf;
    private Label labelFuncionarioRg;
    private Label labelFuncionarioSegurancaNome;
    private Label labelFuncionarioSegurancaSenha;
    private Label labelFuncionarioSegurancaRg;
    private Label labelFuncionarioSegurancaEmail;
    private Label labelFuncionarioNome1;
    private Label labelFuncionarioEmail1;
    private Label labelFuncionarioCpf1;
    private Label labelFuncionarioRg1;
    private Label labelFuncionarioNome11;
    private Label labelFuncionarioEmail11;
    private Label labelFuncionarioCpf11;
    private PasswordField passwordFieldFuncionarioSegurancaSenhaAtual;
    private PasswordField passwordFieldFuncionarioSegurancaNovaSenha;
    private PasswordField passwordFieldFuncionarioSegurancaConfirmarSenha;

    private Funcionario funcionarioLogado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        

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
