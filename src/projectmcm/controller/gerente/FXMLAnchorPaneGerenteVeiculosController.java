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
import projectmcm.model.dao.VeiculoDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Veiculo;

public class FXMLAnchorPaneGerenteVeiculosController implements Initializable {

    @FXML
    private TableView<Veiculo> tableViewVeiculos;
    @FXML
    private TableColumn<Veiculo, String> tableColumnVeiculosMarca;
    @FXML
    private TableColumn<Veiculo, String> tableColumnVeiculosModelo;
    @FXML
    private TableColumn<Veiculo, String> tableColumnVeiculosAno;
    @FXML
    private TextField textFieldPesquisar;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonPesquisar;
    @FXML
    private Label labelVeiculoCor;
    @FXML
    private Label labelVeiculoValor;
    @FXML
    private Label labelVeiculoPlaca;
    @FXML
    private Label labelVeiculoChassi;
    @FXML
    private Label labelVeiculoAnoModelo;
    @FXML
    private Label labelVeiculoAnoFabricacao;
    @FXML
    private Label labelVeiculoDataCompra;
    @FXML
    private Label labelVeiculoObservacoes;
    @FXML
    private Label labelVeiculoMotor;
    @FXML
    private CheckBox checkBoxVeiculoArCondicionado;
    @FXML
    private CheckBox checkBoxVeiculoVidroEletrico;
    @FXML
    private CheckBox checkBoxVeiculoTravaEletrica;
    @FXML
    private CheckBox checkBoxVeiculoDirecaoEletrica;
    @FXML
    private CheckBox checkBoxVeiculoCambioAutomatico;
    @FXML
    private CheckBox checkBoxVeiculoAbs;
    @FXML
    private CheckBox checkBoxVeiculoAirBag;
    @FXML
    private CheckBox checkBoxVeiculoTracao4x4;
    @FXML
    private CheckBox choiceBoxVeiculoStatus;

    private List<Veiculo> listVeiculos;
    private ObservableList<Veiculo> observableListVeiculos;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        veiculoDAO.setConnection(connection);
        carregarTableViewVeiculos();

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        if (!listVeiculos.isEmpty()) {
            tableViewVeiculos.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewVeiculos(newValue));
        }

    }

    public void carregarTableViewVeiculos() {
        tableColumnVeiculosMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableColumnVeiculosModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableColumnVeiculosAno.setCellValueFactory(new PropertyValueFactory<>("ano"));

        listVeiculos = veiculoDAO.listar();
        if (!listVeiculos.isEmpty()) {
            observableListVeiculos = FXCollections.observableArrayList(listVeiculos);
            tableViewVeiculos.setItems(observableListVeiculos);
        }
    }

    public void selecionarItemTableViewVeiculos(Veiculo veiculo) {
        if (veiculo != null) {
            this.labelVeiculoCor.setText(veiculo.getCor());
            this.labelVeiculoValor.setText(String.valueOf(veiculo.getValor()));
            this.labelVeiculoPlaca.setText(veiculo.getPlaca());
            this.labelVeiculoChassi.setText(veiculo.getChassi());
            this.labelVeiculoAnoModelo.setText(veiculo.getAnoModelo().toString());
            this.labelVeiculoAnoFabricacao.setText(veiculo.getAnoFabricacao().toString());
            this.labelVeiculoDataCompra.setText(veiculo.getDataCompra().toString());
            this.labelVeiculoObservacoes.setText(veiculo.getObservacoes());
            this.labelVeiculoMotor.setText(String.valueOf(veiculo.getMotor()));
            this.checkBoxVeiculoArCondicionado.setSelected(veiculo.isArCondicionado());
            this.checkBoxVeiculoVidroEletrico.setSelected(veiculo.isVidroEletrico());
            this.checkBoxVeiculoTravaEletrica.setSelected(veiculo.isTravaEletrica());
            this.checkBoxVeiculoDirecaoEletrica.setSelected(veiculo.isDirecaoEletrica());
            this.checkBoxVeiculoCambioAutomatico.setSelected(veiculo.isCambioAutomatico());
            this.checkBoxVeiculoAbs.setSelected(veiculo.isAbs());
            this.checkBoxVeiculoAirBag.setSelected(veiculo.isAirBag());
            this.checkBoxVeiculoTracao4x4.setSelected(veiculo.isTracao4x4());
            //this.choiceBoxVeiculoStatus.setText(veiculo.getIdStatus());
        } else {
            this.labelVeiculoCor.setText("");
            this.labelVeiculoValor.setText("");
            this.labelVeiculoPlaca.setText("");
            this.labelVeiculoChassi.setText("");
            this.labelVeiculoAnoModelo.setText("");
            this.labelVeiculoAnoFabricacao.setText("");
            this.labelVeiculoDataCompra.setText("");
            this.labelVeiculoObservacoes.setText("");
            this.labelVeiculoMotor.setText("");
            this.checkBoxVeiculoArCondicionado.setSelected(false);
            this.checkBoxVeiculoVidroEletrico.setSelected(false);
            this.checkBoxVeiculoTravaEletrica.setSelected(false);
            this.checkBoxVeiculoDirecaoEletrica.setSelected(false);
            this.checkBoxVeiculoCambioAutomatico.setSelected(false);
            this.checkBoxVeiculoAbs.setSelected(false);
            this.checkBoxVeiculoAirBag.setSelected(false);
            this.checkBoxVeiculoTracao4x4.setSelected(false);
            this.choiceBoxVeiculoStatus.setText("");
        }

    }

    @FXML
    public void handleButtonCadastrar() throws IOException {
        Veiculo veiculo = new Veiculo();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminVeiculosDialog(veiculo);
        if (buttonConfirmarClicked) {
            veiculoDAO.inserir(veiculo);
            carregarTableViewVeiculos();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Veiculo veiculo = null;
        if (!listVeiculos.isEmpty()) {
            veiculo = (Veiculo) tableViewVeiculos.getSelectionModel().getSelectedItem();
        }
        if (veiculo != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminVeiculosDialog(veiculo);
            if (buttonConfirmarClicked) {
                veiculoDAO.alterar(veiculo);
                selecionarItemTableViewVeiculos(null);
                carregarTableViewVeiculos();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um veiculo na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Veiculo veiculo = null;
        if (!listVeiculos.isEmpty()) {
            veiculo = (Veiculo) tableViewVeiculos.getSelectionModel().getSelectedItem();
        }
        if (veiculo != null) {
            veiculoDAO.remover(veiculo);
            carregarTableViewVeiculos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um veiculo na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonPesquisar() throws IOException {
        tableColumnVeiculosMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableColumnVeiculosModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableColumnVeiculosAno.setCellValueFactory(new PropertyValueFactory<>("ano"));

        if (!textFieldPesquisar.getText().equals("")) {
            listVeiculos = veiculoDAO.buscar(textFieldPesquisar.getText());
            if (!listVeiculos.isEmpty()) {
                observableListVeiculos = FXCollections.observableArrayList(listVeiculos);
                tableViewVeiculos.setItems(observableListVeiculos);
            }
        } else {
            carregarTableViewVeiculos();
        }
    }

    public boolean showFXMLAnchorPaneAdminVeiculosDialog(Veiculo veiculo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneGerenteVeiculosDialogController.class.getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerenteVeiculosDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Veiculo");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o veiculo no Controller.
        FXMLAnchorPaneGerenteVeiculosDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setVeiculo(veiculo);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

}
