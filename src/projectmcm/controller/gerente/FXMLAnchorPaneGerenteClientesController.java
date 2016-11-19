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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projectmcm.model.dao.ClienteDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Cliente;

/**
 * FXML Controller class
 *
 * @author vh_ma
 */
public class FXMLAnchorPaneGerenteClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNome;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteCpf;
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
    private Label labelClienteId;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteEmail;    
    @FXML
    private Label labelClienteCpf;
    @FXML
    private Label labelClienteRg;
    @FXML
    private Label labelClienteNascimento;
    @FXML
    private Label labelClienteVinculo;
    @FXML
    private Label labelClienteEndereco;

    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clienteDAO.setConnection(connection);
        carregarTableViewCliente();

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        if (!listClientes.isEmpty())
            tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }

    public void carregarTableViewCliente() {
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listClientes = clienteDAO.listar();
        if (!listClientes.isEmpty()){
            observableListClientes = FXCollections.observableArrayList(listClientes);
            tableViewClientes.setItems(observableListClientes);
        }
    }
    
    public void selecionarItemTableViewClientes(Cliente cliente){
        if (cliente != null) {
            this.labelClienteId.setText(String.valueOf(cliente.getIdCliente()));
            labelClienteNome.setText(cliente.getNome());
            labelClienteEmail.setText(cliente.getEmail());
            labelClienteCpf.setText(cliente.getCpf());
            labelClienteRg.setText(cliente.getRg());
            this.labelClienteNascimento.setText(cliente.getDataNascimento().toString());
            this.labelClienteVinculo.setText(cliente.getDataVinculo().toString());
            this.labelClienteEndereco.setText(cliente.getEndereco().toString());
        } else {
            this.labelClienteId.setText("");
            labelClienteNome.setText("");
            labelClienteEmail.setText("");
            labelClienteCpf.setText("");
            labelClienteRg.setText("");
            this.labelClienteNascimento.setText("");
            this.labelClienteVinculo.setText("");
            this.labelClienteEndereco.setText("");
        }

    }
    
    @FXML
    public void handleButtonCadastrar() throws IOException {
        Cliente cliente = new Cliente();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneGerenteClientesDialog(cliente);
        if (buttonConfirmarClicked) {
            clienteDAO.inserir(cliente);
            carregarTableViewCliente();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Cliente cliente = null;
        if (!listClientes.isEmpty())
            cliente = (Cliente)tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneGerenteClientesDialog(cliente);
            if (buttonConfirmarClicked) {
                clienteDAO.alterar(cliente);
                selecionarItemTableViewClientes(null);
                carregarTableViewCliente();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um locador na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Cliente cliente = null;
        if (!listClientes.isEmpty())
            cliente = (Cliente)tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            clienteDAO.remover(cliente);
            carregarTableViewCliente();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um locador na Tabela!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonPesquisar() throws IOException {
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        if (!textFieldPesquisar.getText().equals("")){
            listClientes = clienteDAO.buscarNome(textFieldPesquisar.getText());
            if (!listClientes.isEmpty()){
                observableListClientes = FXCollections.observableArrayList(listClientes);
                tableViewClientes.setItems(observableListClientes);
            }
        }else{
            carregarTableViewCliente();
        }
    }
    
    public boolean showFXMLAnchorPaneGerenteClientesDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneGerenteClientesDialogController.class.getResource("/projectmcm/view/gerente/FXMLAnchorPaneGerenteClientesDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Funcionario");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o locador no Controller.
        FXMLAnchorPaneGerenteClientesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }
    
}
