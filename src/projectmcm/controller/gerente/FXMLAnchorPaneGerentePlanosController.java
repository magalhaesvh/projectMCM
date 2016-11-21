/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller.gerente;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projectmcm.model.dao.PlanoDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Funcionario;
import projectmcm.model.domain.Plano;

/**
 * FXML Controller class
 *
 * @author vh_ma
 */
public class FXMLAnchorPaneGerentePlanosController implements Initializable {

    @FXML
    private TableView<Plano> tableViewPlanos;
    @FXML
    private TableColumn<Plano, String> tableColumnPlanoNome;
    @FXML
    private TableColumn<Plano, String> tableColumnPlanoTipo;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonPesquisar;
    @FXML
    private TextField textFieldPesquisar;
    @FXML
    private Label labelPlanoId;
    @FXML
    private Label labelPlanoNome;
    @FXML
    private Label labelPlanoRegulamento;
    @FXML
    private Label labelPlanoDescricao;
    @FXML
    private Label labelPlanoQuilometragem;
    @FXML
    private Label labelPlanoCustoFixo;
    @FXML
    private Label labelPlanoDiaria;
    @FXML
    private CheckBox checkBoxPlanoQuilometragem;
    @FXML
    private CheckBox checkBoxPlanoCustoFixo;
    @FXML
    private CheckBox checkBoxDiaria;
    
    private Funcionario logado;

    private List<Plano> listPlanos;
    private ObservableList<Plano> observableListPlanos;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final PlanoDAO planoDAO = new PlanoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        planoDAO.setConnection(connection);
        this.carregarTableViewPlanos();
        
        if (!listPlanos.isEmpty())
            tableViewPlanos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewPlanos(newValue));
    }

    public void carregarTableViewPlanos() {
        tableColumnPlanoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnPlanoTipo.setCellValueFactory(new PropertyValueFactory<>("id_tipo"));

        listPlanos = planoDAO.listar();
        if (!listPlanos.isEmpty()) {
            observableListPlanos = FXCollections.observableArrayList(listPlanos);
            tableViewPlanos.setItems(observableListPlanos);
        }
    }

    public void selecionarItemTableViewPlanos(Plano plano) {
        if (plano != null) {
            this.labelPlanoId.setText(String.valueOf(plano.getIdPlano()));
            this.labelPlanoNome.setText(plano.getNome());
            this.labelPlanoRegulamento.setText(plano.getRegulamento());
            this.labelPlanoDescricao.setText(plano.getDescricao());
            this.labelPlanoQuilometragem.setText(String.valueOf(plano.getValorQuilometragem()));
            this.labelPlanoCustoFixo.setText(String.valueOf(plano.getValorCusto()));
            this.labelPlanoDiaria.setText(String.valueOf(plano.getValorDiaria()));
        } else{
            this.labelPlanoId.setText("");
            this.labelPlanoNome.setText("");
            this.labelPlanoRegulamento.setText("");
            this.labelPlanoDescricao.setText("");
            this.labelPlanoQuilometragem.setText("");
            this.labelPlanoCustoFixo.setText("");
            this.labelPlanoDiaria.setText("");
        }
    }
    
    public void handleButtonCadastrar() throws IOException {
        Plano plano = new Plano();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneGerentePlanosDialog(plano);
        if (buttonConfirmarClicked) {
            planoDAO.inserir(plano);
            carregarTableViewPlanos();
        }
    }
    
    public void handleButtonAlterar() throws IOException {
        Plano plano = null;
        if (!listPlanos.isEmpty())
            plano = (Plano)tableViewPlanos.getSelectionModel().getSelectedItem();
        if (plano != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneGerentePlanosDialog(plano);
            if (buttonConfirmarClicked) {
                planoDAO.alterar(plano);
                selecionarItemTableViewPlanos(null);
                carregarTableViewPlanos();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um plano na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Plano plano = null;
        if (!listPlanos.isEmpty())
            plano = (Plano)tableViewPlanos.getSelectionModel().getSelectedItem();
        if (plano != null) {
            planoDAO.remover(plano);
            carregarTableViewPlanos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um plano na Tabela!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonPesquisar() throws IOException {
        tableColumnPlanoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnPlanoTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        if (!textFieldPesquisar.getText().equals("")){
            listPlanos = planoDAO.buscarNome(textFieldPesquisar.getText());
            if (!listPlanos.isEmpty()){
                observableListPlanos = FXCollections.observableArrayList(listPlanos);
                tableViewPlanos.setItems(observableListPlanos);
            }
        }else{
            carregarTableViewPlanos();
        }
    }
    
    public boolean showFXMLAnchorPaneGerentePlanosDialog(Plano plano) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneGerentePlanosDialogController.class.getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerentePlanosDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Plano");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o locador no Controller.
        FXMLAnchorPaneGerentePlanosDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPlano(plano);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }
}
