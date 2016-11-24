
package projectmcm.controller.locador;

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
import projectmcm.controller.gerente.FXMLAnchorPaneGerenteVeiculosController;
import projectmcm.model.domain.Funcionario;

public class FXMLVBoxLocadorController implements Initializable {
    
    private Funcionario logado;
    @FXML
    private Menu menuAdmin;    
    @FXML
    private AnchorPane anchorPane;
    
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void handleMenuItemGerenciarSeguranca() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectmcm/view/FXMLAnchorPaneFuncionarioSeguranca.fxml"));
        AnchorPane a = (AnchorPane) fxmlLoader.load();

        anchorPane.getChildren().setAll(a);
        FXMLAnchorPaneFuncionarioSegurancaController funcionarioSeguranca = fxmlLoader.<FXMLAnchorPaneFuncionarioSegurancaController>getController();
        funcionarioSeguranca.setFuncionarioLogado(this.logado);
    }
    
    @FXML
    public void handleMenuItemGerenciarClientes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerenteClientes.fxml"));
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
    public void handleMenuItemLocacaoRealizar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projectmcm/view/FXMLAnchorPaneRealizarLocacao.fxml"));
        AnchorPane a = (AnchorPane) fxmlLoader.load();

        anchorPane.getChildren().setAll(a);
        FXMLAnchorPaneRealizarLocacaoController funcionarioSeguranca = fxmlLoader.<FXMLAnchorPaneRealizarLocacaoController>getController();
        funcionarioSeguranca.setLogado(this.logado);
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
