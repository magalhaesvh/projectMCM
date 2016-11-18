/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import projectmcm.model.dao.FuncionarioDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Funcionario;
import util.Seguranca;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class FXMLAnchorPaneFuncionarioSegurancaController implements Initializable {

    @FXML
    private TextField textFieldFuncionarioSegurancaNome;
    @FXML
    private TextField textFieldFuncionarioSegurancaEmail;
    @FXML
    private TextField textFieldFuncionarioSegurancaCpf;
    @FXML
    private TextField textFieldFuncionarioSegurancaRg;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelCpf;
    @FXML
    private Label labelRg;
    @FXML
    private PasswordField passwordFieldFuncionarioSegurancaSenhaAtual;
    @FXML
    private PasswordField passwordFieldFuncionarioSegurancaNovaSenha;
    @FXML
    private PasswordField passwordFieldFuncionarioSegurancaConfirmarSenha;

    private Funcionario funcionarioLogado;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        funcionarioDAO.setConnection(connection);
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
        textFieldFuncionarioSegurancaNome.setText(funcionarioLogado.getNome());
        textFieldFuncionarioSegurancaEmail.setText(funcionarioLogado.getEmail());
        textFieldFuncionarioSegurancaCpf.setText(funcionarioLogado.getCpf());
        textFieldFuncionarioSegurancaRg.setText(funcionarioLogado.getRg());
        labelNome.setText(funcionarioLogado.getNome());
        labelEmail.setText(funcionarioLogado.getEmail());
        labelCpf.setText(funcionarioLogado.getCpf());
        labelRg.setText(funcionarioLogado.getRg());
    }
        
    public void handleSalvarDados(){
        funcionarioLogado.setNome(textFieldFuncionarioSegurancaNome.getText());
        funcionarioLogado.setCpf(textFieldFuncionarioSegurancaCpf.getText());
        funcionarioLogado.setEmail(textFieldFuncionarioSegurancaEmail.getText());
        funcionarioLogado.setRg(textFieldFuncionarioSegurancaRg.getText());
        if (!funcionarioDAO.alterar(funcionarioLogado)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ocorreu um erro tente novamente!");
            alert.show();
        }else{
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
            this.setFuncionarioLogado(funcionarioLogado);
        }
    }

    public void handleAlterarSenha(){
        if (this.passwordFieldFuncionarioSegurancaSenhaAtual.getText().length() > 0 || this.passwordFieldFuncionarioSegurancaNovaSenha.getText().length() > 0 || this.passwordFieldFuncionarioSegurancaConfirmarSenha.getText().length() > 0){
            if (Seguranca.criptografa(this.passwordFieldFuncionarioSegurancaSenhaAtual.getText()).equals(this.funcionarioLogado.getSenha())){
                if (this.passwordFieldFuncionarioSegurancaNovaSenha.getText().equals(this.passwordFieldFuncionarioSegurancaConfirmarSenha.getText())){
                    this.funcionarioLogado.setSenha(Seguranca.criptografa(this.passwordFieldFuncionarioSegurancaNovaSenha.getText()));
                    this.funcionarioDAO.alterar(this.funcionarioLogado);
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
                    this.passwordFieldFuncionarioSegurancaConfirmarSenha.setText("");
                    this.passwordFieldFuncionarioSegurancaNovaSenha.setText("");
                    this.passwordFieldFuncionarioSegurancaSenhaAtual.setText("");
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("A senha nova e sua confirmação não são iguais!");
                    alert.show();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Senha inválida!");
            alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Preencha todos os campos para alterar a senha!");
            alert.show();
        }
    }
}
