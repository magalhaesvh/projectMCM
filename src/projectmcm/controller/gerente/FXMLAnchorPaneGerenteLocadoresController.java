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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projectmcm.model.dao.LocadorDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Locador;

public class FXMLAnchorPaneGerenteLocadoresController implements Initializable {

    @FXML
    private TableView<Locador> tableViewLocadores;
    @FXML
    private TableColumn<Locador, String> tableColumnLocadorNome;
    @FXML
    private TableColumn<Locador, String> tableColumnLocadorCpf;
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
    private Label labelLocadorCodigo;
    @FXML
    private Label labelLocadorNome;
    @FXML
    private Label labelLocadorEmail;    
    @FXML
    private Label labelLocadorCpf;
    @FXML
    private Label labelLocadorRg;
    @FXML
    private Label labelLocadorDataContratacao;

    private List<Locador> listLocadores;
    private ObservableList<Locador> observableListLocadores;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final LocadorDAO locadorDAO = new LocadorDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        locadorDAO.setConnection(connection);
        carregarTableViewLocador();

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        if (!listLocadores.isEmpty())
            tableViewLocadores.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewLocadores(newValue));

    }

    public void carregarTableViewLocador() {
        tableColumnLocadorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnLocadorCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listLocadores = locadorDAO.listar();
        if (!listLocadores.isEmpty()){
            observableListLocadores = FXCollections.observableArrayList(listLocadores);
            tableViewLocadores.setItems(observableListLocadores);
        }
    }
    
    public void selecionarItemTableViewLocadores(Locador locador){
        if (locador != null) {
            labelLocadorNome.setText(locador.getNome());
            labelLocadorEmail.setText(locador.getEmail());
            labelLocadorCpf.setText(locador.getCpf());
            labelLocadorRg.setText(locador.getRg());
            //labelLocadorDataContratacao.setText(locador.getDataContratacao().toString());
        } else {
            labelLocadorNome.setText("");
            labelLocadorEmail.setText("");
            labelLocadorCpf.setText("");
            labelLocadorRg.setText("");
            labelLocadorDataContratacao.setText("");
        }

    }
    
    @FXML
    public void handleButtonCadastrar() throws IOException {
        Locador locador = new Locador();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminLocadoresDialog(locador);
        if (buttonConfirmarClicked) {
            locadorDAO.inserir(locador);
            carregarTableViewLocador();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Locador locador = null;
        if (!listLocadores.isEmpty())
            locador = (Locador)tableViewLocadores.getSelectionModel().getSelectedItem();
        if (locador != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneAdminLocadoresDialog(locador);
            if (buttonConfirmarClicked) {
                locadorDAO.alterar(locador);
                selecionarItemTableViewLocadores(null);
                carregarTableViewLocador();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um locador na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Locador locador = null;
        if (!listLocadores.isEmpty())
            locador = (Locador)tableViewLocadores.getSelectionModel().getSelectedItem();
        if (locador != null) {
            locadorDAO.remover(locador);
            carregarTableViewLocador();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um locador na Tabela!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonPesquisar() throws IOException {
        tableColumnLocadorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnLocadorCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        if (!textFieldPesquisar.getText().equals("")){
            listLocadores = locadorDAO.buscar(textFieldPesquisar.getText());
            if (!listLocadores.isEmpty()){
                observableListLocadores = FXCollections.observableArrayList(listLocadores);
                tableViewLocadores.setItems(observableListLocadores);
            }
        }else{
            carregarTableViewLocador();
        }
    }
    
    public boolean showFXMLAnchorPaneAdminLocadoresDialog(Locador locador) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneGerenteLocadoresDialogController.class.getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerenteLocadoresDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Locador");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o locador no Controller.
        FXMLAnchorPaneGerenteLocadoresDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setLocador(locador);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }



}
