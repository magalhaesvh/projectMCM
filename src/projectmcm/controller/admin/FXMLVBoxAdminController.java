/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projectmcm.controller.FXMLAnchorPaneFuncionarioSegurancaController;
import projectmcm.controller.FXMLLoginPrincipalController;
import projectmcm.model.domain.Funcionario;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class FXMLVBoxAdminController implements Initializable {

    private Funcionario logado;

    @FXML
    private MenuItem menuItemAdminSeguranca;
    @FXML
    private MenuItem menuItemAdminSair;
    @FXML
    private MenuItem menuItemGerenciarAgencias;
    @FXML
    private MenuItem menuItemGerenciarGerentes;
    @FXML
    private MenuItem menuItemGerenciarSituacoes;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Menu menuAdmin;
    @FXML
    private FXMLAnchorPaneFuncionarioSegurancaController funcSeguranca;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void handleMenuItemGerenciarAgencias() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/admin/FXMLAnchorPaneAdminAgencias.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void handleMenuItemGerenciarGerentes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/admin/FXMLAnchorPaneAdminGerentes.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void handleMenuItemGerenciarSituacoes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/admin/FXMLAnchorPaneAdminSituacoes.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void handleMenuItemAdminSeguranca() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectmcm/view/FXMLAnchorPaneFuncionarioSeguranca.fxml"));
        AnchorPane a = (AnchorPane) fxmlLoader.load();

        anchorPane.getChildren().setAll(a);
        FXMLAnchorPaneFuncionarioSegurancaController funcionarioSeguranca = fxmlLoader.<FXMLAnchorPaneFuncionarioSegurancaController>getController();
        funcionarioSeguranca.setFuncionarioLogado(this.logado);
    }

    @FXML
    public void handleSair() throws IOException {
        this.logado = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectmcm/view/FXMLLoginPrincipal.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLLoginPrincipalController controller = fxmlLoader.<FXMLLoginPrincipalController>getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Aluguel de carros - Project MCM");
        stage.setResizable(false);
        stage.show();
    }

    public Funcionario getLogado() {
        return logado;
    }

    public void setLogado(Funcionario logado) {
        this.logado = logado;
        menuAdmin.setText("Meus dados");
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
