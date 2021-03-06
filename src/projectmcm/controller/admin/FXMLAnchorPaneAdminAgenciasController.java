package projectmcm.controller.admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import projectmcm.model.dao.AgenciaDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Agencia;

public class FXMLAnchorPaneAdminAgenciasController implements Initializable {

    @FXML
    private TableView<Agencia> tableViewAgencias;
    @FXML
    private TableColumn<Agencia, String> tableColumnAgenciaNome;
    @FXML
    private TableColumn<Agencia, String> tableColumnAgenciaCnpj;
    @FXML
    private TextField textFieldPesquisar;
    @FXML
    private Label labelAgenciaNome;
    @FXML
    private Label labelAgenciaCnpj;
    @FXML
    private PieChart graficoVeiculoAgencia;

    private List<Agencia> listAgencias;
    private ObservableList<Agencia> observableListAgencias= FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> pieChartData;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final AgenciaDAO agenciaDAO = new AgenciaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agenciaDAO.setConnection(connection);
        carregarTableViewAgencia();

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewAgencias.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewAgencias(newValue));

    }

    public void carregarTableViewAgencia() {
        tableColumnAgenciaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnAgenciaCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));

        listAgencias = agenciaDAO.listar();

        observableListAgencias = FXCollections.observableArrayList(listAgencias);
        tableViewAgencias.setItems(observableListAgencias);        
    }

    public void selecionarItemTableViewAgencias(Agencia agencia) {
        if (agencia != null) {
            labelAgenciaNome.setText(agencia.getNome());
            labelAgenciaCnpj.setText(agencia.getCnpj());
            pieChartData = FXCollections.observableArrayList();

            Map<String, Integer> dados = agenciaDAO.listarStatusVeiculos(agencia);
            if (!dados.isEmpty()) {
                for (Map.Entry<String, Integer> entry : dados.entrySet()) {
                    pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
                    graficoVeiculoAgencia.setData(pieChartData);
                }
            } else {
                pieChartData.clear();
                graficoVeiculoAgencia.setData(pieChartData);
            }

        } else {
            labelAgenciaNome.setText("");
            labelAgenciaCnpj.setText("");
            graficoVeiculoAgencia.setData(null);
        }

    }

    @FXML
    public void handleButtonCadastrar() throws IOException {
        Agencia agencia = new Agencia();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminAgenciasDialog(agencia);
        if (buttonConfirmarClicked) {
            if (!agenciaDAO.buscar(agencia.getCnpj()).isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro no cadastro");
                alert.setHeaderText("CNPJ já cadastrado");
                alert.show();
            }else{
                agenciaDAO.inserir(agencia);
                carregarTableViewAgencia();
            }
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Agencia agencia = tableViewAgencias.getSelectionModel().getSelectedItem();
        if (agencia != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminAgenciasDialog(agencia);
            if (buttonConfirmarClicked) {
                agenciaDAO.alterar(agencia);
                selecionarItemTableViewAgencias(null);
                carregarTableViewAgencia();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um agencia na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Agencia agencia = tableViewAgencias.getSelectionModel().getSelectedItem();
        if (agencia != null) {
            agenciaDAO.remover(agencia);
            carregarTableViewAgencia();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um agencia na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonPesquisar() throws IOException {
        tableColumnAgenciaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnAgenciaCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));

        if (!textFieldPesquisar.getText().equals("")) {
            listAgencias = agenciaDAO.buscar(textFieldPesquisar.getText());

            observableListAgencias = FXCollections.observableArrayList(listAgencias);
            tableViewAgencias.setItems(observableListAgencias);
        } else {
            carregarTableViewAgencia();
        }
    }

    public boolean showFXMLAnchorPaneAdminAgenciasDialog(Agencia agencia) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneAdminAgenciasDialogController.class.getResource("/projectmcm/view/admin/FXMLAnchorPaneAdminAgenciasDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Agência");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando a agencia no Controller.
        FXMLAnchorPaneAdminAgenciasDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAgencia(agencia);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }
    
    @FXML
    public void handleGeraRelatorio(){
        Agencia agencia = tableViewAgencias.getSelectionModel().getSelectedItem();
        String src="Relatorios/geral.jasper";
        JasperPrint jasperPrint = null;
        HashMap<String, Object> map = new HashMap();
        map.put("id", agencia.getIdAgencia());
        try {
            jasperPrint = JasperFillManager.fillReport(src, map, this.connection);
        } catch (JRException ex) {
            Logger.getLogger(FXMLAnchorPaneAdminAgenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        JasperViewer view = new JasperViewer(jasperPrint, false);
        view.setVisible(true);
    }

}
