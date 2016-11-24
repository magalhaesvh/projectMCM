package projectmcm.controller.gerente;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import projectmcm.controller.admin.FXMLAnchorPaneAdminAgenciasController;
import projectmcm.model.dao.VeiculoDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Funcionario;
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
    private Label labelGerenteVeiculoCor;
    @FXML
    private Label labelGerenteVeiculoValor;
    @FXML
    private Label labelGerenteVeiculoPlaca;
    @FXML
    private Label labelGerenteVeiculoChassi;
    @FXML
    private Label labelGerenteVeiculoAnoModelo;
    @FXML
    private Label labelGerenteVeiculoAnoFabricacao;
    @FXML
    private Label labelGerenteVeiculoObservacoes;
    @FXML
    private Label labelGerenteVeiculoMotor;
    @FXML
    private Label labelGerenteVeiculoModelo;
    @FXML
    private Label labelGerenteVeiculoMarca;
    @FXML
    private Label labelGerenteVeiculoId;    
    @FXML
    private CheckBox checkBoxGerenteVeiculoArCondicionado;
    @FXML
    private CheckBox checkBoxGerenteVeiculoVidroEletrico;
    @FXML
    private CheckBox checkBoxGerenteVeiculoTravaEletrica;
    @FXML
    private CheckBox checkBoxGerenteVeiculoDirecaoEletrica;
    @FXML
    private CheckBox checkBoxGerenteVeiculoCambioAutomatico;
    @FXML
    private CheckBox checkBoxGerenteVeiculoAbs;
    @FXML
    private CheckBox checkBoxGerenteVeiculoAirBag;
    @FXML
    private CheckBox checkBoxGerenteVeiculoTracao4x4;
    @FXML
    private Label labelGerenteVeiculoStatus;
    
    private Funcionario logado;

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
            FilteredList<Veiculo> filteredData = new FilteredList<>(this.observableListVeiculos, p -> true);
        textFieldPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(cliente -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (cliente.getPlaca().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (cliente.getModelo().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (cliente.getMarca().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (String.valueOf(cliente.getIdVeiculo()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (String.valueOf(cliente.getMotor()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (String.valueOf(cliente.getAnoFabricacao()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
            tableViewVeiculos.setItems(filteredData);
        });
        }

    }

    public void carregarTableViewVeiculos() {
        tableColumnVeiculosMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableColumnVeiculosModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableColumnVeiculosAno.setCellValueFactory(new PropertyValueFactory<>("placa"));

        listVeiculos = veiculoDAO.listar();
        if (!listVeiculos.isEmpty()) {
            observableListVeiculos = FXCollections.observableArrayList(listVeiculos);
            tableViewVeiculos.setItems(observableListVeiculos);
        }
    }

    public void selecionarItemTableViewVeiculos(Veiculo veiculo) {
        if (veiculo != null) {
            this.labelGerenteVeiculoId.setText(veiculo.getCor());
            this.labelGerenteVeiculoCor.setText(veiculo.getCor());
            this.labelGerenteVeiculoValor.setText(String.valueOf(veiculo.getValor()));
            this.labelGerenteVeiculoPlaca.setText(veiculo.getPlaca());
            this.labelGerenteVeiculoChassi.setText(veiculo.getChassi());
            this.labelGerenteVeiculoAnoModelo.setText(String.valueOf(veiculo.getAnoModelo()));
            this.labelGerenteVeiculoAnoFabricacao.setText(String.valueOf(veiculo.getAnoFabricacao()));
            this.labelGerenteVeiculoObservacoes.setText(veiculo.getObservacoes());
            this.labelGerenteVeiculoMotor.setText(String.valueOf(veiculo.getMotor()));
            this.checkBoxGerenteVeiculoArCondicionado.setSelected(veiculo.isArCondicionado());
            this.checkBoxGerenteVeiculoVidroEletrico.setSelected(veiculo.isVidroEletrico());
            this.checkBoxGerenteVeiculoTravaEletrica.setSelected(veiculo.isTravaEletrica());
            this.checkBoxGerenteVeiculoDirecaoEletrica.setSelected(veiculo.isDirecaoEletrica());
            this.checkBoxGerenteVeiculoCambioAutomatico.setSelected(veiculo.isCambioAutomatico());
            this.checkBoxGerenteVeiculoAbs.setSelected(veiculo.isAbs());
            this.checkBoxGerenteVeiculoAirBag.setSelected(veiculo.isAirBag());
            this.checkBoxGerenteVeiculoTracao4x4.setSelected(veiculo.isTracao4x4());
            this.labelGerenteVeiculoMarca.setText(veiculo.getMarca());
            this.labelGerenteVeiculoModelo.setText(veiculo.getModelo());
            this.labelGerenteVeiculoStatus.setText(veiculo.getStatus().getNome());
        } else {
            this.labelGerenteVeiculoId.setText("");
            this.labelGerenteVeiculoCor.setText("");
            this.labelGerenteVeiculoValor.setText("");
            this.labelGerenteVeiculoPlaca.setText("");
            this.labelGerenteVeiculoChassi.setText("");
            this.labelGerenteVeiculoAnoModelo.setText("");
            this.labelGerenteVeiculoAnoFabricacao.setText("");
            this.labelGerenteVeiculoObservacoes.setText("");
            this.labelGerenteVeiculoMotor.setText("");
            this.checkBoxGerenteVeiculoArCondicionado.setSelected(false);
            this.checkBoxGerenteVeiculoVidroEletrico.setSelected(false);
            this.checkBoxGerenteVeiculoTravaEletrica.setSelected(false);
            this.checkBoxGerenteVeiculoDirecaoEletrica.setSelected(false);
            this.checkBoxGerenteVeiculoCambioAutomatico.setSelected(false);
            this.checkBoxGerenteVeiculoAbs.setSelected(false);
            this.checkBoxGerenteVeiculoAirBag.setSelected(false);
            this.checkBoxGerenteVeiculoTracao4x4.setSelected(false);
            this.labelGerenteVeiculoMarca.setText("");
            this.labelGerenteVeiculoModelo.setText("");
            this.labelGerenteVeiculoStatus.setText("");
        }

    }

    @FXML
    public void handleButtonCadastrar() throws IOException {
        Veiculo veiculo = new Veiculo();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminVeiculosDialog(veiculo);
        if (buttonConfirmarClicked) {
            veiculo.setAgencia(logado.getAgencia());
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
                veiculo.setAgencia(logado.getAgencia());
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
    
    @FXML
    public void handleGeraRelatorio(){
        String src="Relatorios/carros.jasper";
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(src, null, this.connection);
        } catch (JRException ex) {
            Logger.getLogger(FXMLAnchorPaneAdminAgenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        JasperViewer view = new JasperViewer(jasperPrint, false);
        view.setVisible(true);
    }

    public Funcionario getLogado() {
        return logado;
    }

    public void setLogado(Funcionario logado) {
        this.logado = logado;
    }

}
