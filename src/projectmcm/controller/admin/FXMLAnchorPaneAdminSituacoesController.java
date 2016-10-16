package projectmcm.controller.admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projectmcm.model.dao.StatusDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Status;

public abstract class FXMLAnchorPaneAdminSituacoesController implements Initializable {
/*
    @FXML
    private TableView<Status> tableViewStatus;
    @FXML
    private TableColumn<Status, String> tableColumnStatusNome;
    @FXML
    private TableColumn<Status, String> tableColumnStatusTipo;
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
    private Label labelStatusId;
    @FXML
    private Label labelStatusNome;
    @FXML
    private Label labelStatusTipo;
    @FXML
    private PieChart graficoQuantidadeItensPorStatus;

    private List<Status> listStatus;
    private ObservableList<Status> observableListStatus;
    private ObservableList<PieChart.Data> pieChartData;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final StatusDAO statusDAO = new StatusDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusDAO.setConnection(connection);
        carregarTableViewStatus();

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewStatus.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewStatus(newValue));

    }

    public void carregarTableViewStatus() {
        tableColumnStatusNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnStatusTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        listStatus = statusDAO.listar();

        observableListStatus = FXCollections.observableArrayList(listStatus);
        tableViewStatus.setItems(observableListStatus);
    }

    public void selecionarItemTableViewStatus(Status status) {
        if (status != null) {
            labelStatusNome.setText(status.getNome());
            labelStatusTipo.setText(String.valueOf(status.getTipo()));
            pieChartData = FXCollections.observableArrayList();

            Map<String, Integer> dados = statusDAO.quantidadeItensPorStatus(status);
            if (!dados.isEmpty()) {
                for (Map.Entry<String, Integer> entry : dados.entrySet()) {
                    pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
                    graficoQuantidadeItensPorStatus.setData(pieChartData);
                }
            } else {
                pieChartData.add(new PieChart.Data("", 0));
                graficoQuantidadeItensPorStatus.setData(pieChartData);
            }

        } else {
            labelStatusNome.setText("");
            labelStatusTipo.setText("");
            graficoQuantidadeItensPorStatus.setData(null);
        }

    }

    @FXML
    public void handleButtonCadastrar() throws IOException {
        Status status = new Status();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminStatusDialog(status);
        if (buttonConfirmarClicked) {
            statusDAO.inserir(status);
            carregarTableViewStatus();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Status status = tableViewStatus.getSelectionModel().getSelectedItem();
        if (status != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminStatusDialog(status);
            if (buttonConfirmarClicked) {
                statusDAO.alterar(status);
                selecionarItemTableViewStatus(null);
                carregarTableViewStatus();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um status na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Status status = tableViewStatus.getSelectionModel().getSelectedItem();
        if (status != null) {
            statusDAO.remover(status);
            carregarTableViewStatus();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um status na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonPesquisar() throws IOException {
        tableColumnStatusNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnStatusTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        if (!textFieldPesquisar.getText().equals("")) {
            listStatus = statusDAO.buscar(textFieldPesquisar.getText());

            observableListStatus = FXCollections.observableArrayList(listStatus);
            tableViewStatus.setItems(observableListStatus);
        } else {
            carregarTableViewStatus();
        }
    }

    public boolean showFXMLAnchorPaneAdminStatusDialog(Status status) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneAdminSituacoesDialogController.class.getResource("/projectmcm/view/admin/FXMLAnchorPaneAdminSituacoesDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Situação");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o status no Controller.
        FXMLAnchorPaneAdminSituacoesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setSituacao(status);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }*/

}
