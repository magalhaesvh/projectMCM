/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller.gerente;

import projectmcm.controller.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projectmcm.model.domain.Funcionario;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class FXMLVBoxGerenteController implements Initializable {
    
    private Funcionario logado;
    @FXML
    private Menu menuAdmin;
    @FXML
    private MenuItem menuItemGerenteSeguranca;
    @FXML
    private MenuItem menuItemGerenteSair;
    @FXML
    private MenuItem menuItemGerenciarVeiculos;
    @FXML
    private MenuItem menuItemGerenciarLocadores;
    @FXML
    private MenuItem menuItemGerenciarClientes;
    @FXML
    private MenuItem menuItemGerenciarPlanos;
    @FXML
    private MenuItem menuItemGerenciarPromocoes;      
    @FXML
    private AnchorPane anchorPane;
    
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void handleMenuItemGerenciarSeguranca() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerenteSeguranca.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGerenciarVeiculos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerenteVeiculos.fxml"));
        AnchorPane a = (AnchorPane) fxmlLoader.load();
        FXMLAnchorPaneGerenteVeiculosController controller = fxmlLoader.<FXMLAnchorPaneGerenteVeiculosController>getController();
        controller.setLogado(this.getLogado());
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGerenciarLocadores() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerenteLocadores.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGerenciarClientes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerenteClientes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGerenciarPlanos() throws IOException {
       //                            erro aqui -v
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerentePlanos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGerenciarPromocoes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerentePromocoes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemLocacaoRealizar() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/FXMLAnchorPaneRealizarLocacao.fxml"));
        anchorPane.getChildren().setAll(a);
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
    
    @FXML
    public void teste(){
        System.out.println("teste");
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
