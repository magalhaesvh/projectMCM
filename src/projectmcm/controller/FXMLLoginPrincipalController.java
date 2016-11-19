package projectmcm.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projectmcm.controller.admin.FXMLVBoxAdminController;
import projectmcm.controller.gerente.FXMLVBoxGerenteController;
import projectmcm.model.dao.FuncionarioDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Funcionario;
import util.Seguranca;

public class FXMLLoginPrincipalController implements Initializable {

    @FXML
    private Button loginButtonEntrar;
    @FXML
    private TextField loginTextFieldEmail;
    @FXML
    private PasswordField loginPassFieldSenha;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Stage dialogStage;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        funcionarioDAO.setConnection(connection);
    }

    public boolean handleLoginButtonEntrar() throws IOException {
        if (!Seguranca.validarEmail(loginTextFieldEmail.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Formato de email não válido");
            alert.showAndWait();
            return false;
        } else {
            funcionarioDAO.setConnection(connection);
            Funcionario funcionario = new Funcionario();
            funcionario.setEmail(loginTextFieldEmail.getText());
            funcionario.setSenha(Seguranca.criptografa(loginPassFieldSenha.getText()));
            funcionario = funcionarioDAO.logar(funcionario);
            if (funcionario == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Senha ou email inválidos");
                alert.showAndWait();
            } else {
                if (funcionario.getTipo() == 1) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectmcm/view/admin/FXMLVBoxAdmin.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    FXMLVBoxAdminController controller = fxmlLoader.<FXMLVBoxAdminController>getController();
                    controller.setLogado(funcionario);
                    controller.setStage(stage);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Aluguel de carros - Project MCM");
                    stage.setResizable(false);
                    stage.show();
                } else if (funcionario.getTipo() == 2) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectmcm/view/gerente/FXMLVBoxGerente.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    FXMLVBoxGerenteController controller = fxmlLoader.<FXMLVBoxGerenteController>getController();
                    controller.setLogado(funcionario);
                    controller.setStage(stage);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Aluguel de carros - Project MCM");
                    stage.setResizable(false);
                    stage.show();
                }
                return true;
            }
            return false;
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
