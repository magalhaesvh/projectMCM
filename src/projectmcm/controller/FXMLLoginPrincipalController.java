package projectmcm.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projectmcm.model.dao.FuncionarioDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Funcionario;

public class FXMLLoginPrincipalController implements Initializable {
    
    @FXML
    private Button loginButtonEntrar;
    @FXML
    private TextField loginTextFieldEmail;
    @FXML
    private PasswordField loginPassFieldSenha;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO() {
        @Override
        public Object listar() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object buscar(String texto) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    private Funcionario funcionario = new Funcionario();
    private Stage dialogStage;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
        
    public boolean handleLoginButtonEntrar(){
        funcionarioDAO.setConnection(connection);
        getAux().setEmail(loginTextFieldEmail.getText());
        getAux().setSenha(loginPassFieldSenha.getText());
        setAux(funcionarioDAO.logar(getAux()));
        if (getAux() == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Senha ou email inválidos");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Você logou!");
            alert.showAndWait();
            return true;
        }
        return false;
        
    }

    public Funcionario getAux() {
        return funcionario;
    }

    public void setAux(Funcionario aux) {
        this.funcionario = aux;
    }
    
}
