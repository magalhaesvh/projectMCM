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
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class FXMLVBoxGerenteController implements Initializable {

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void handleMenuItemGerenciarVeiculos() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerenteVeiculos.fxml"));
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
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerentePlanos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGerenciarPromocoes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerentePromocoes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void teste(){
        System.out.println("teste");
    }
    
    
}
