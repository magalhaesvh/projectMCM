/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller;

import java.net.URL;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import projectmcm.model.dao.ClienteDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Cliente;
import projectmcm.model.domain.Locacao;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class FXMLAnchorPaneRealizarLocacaoController implements Initializable {

    private int passo = 0;
    private Locacao locacao = new Locacao();

    @FXML
    private TextField textFieldPesquisar;
    @FXML
    private RadioButton radioTodasAgencias;
    @FXML
    private RadioButton radioEstaAgencia;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn tableColumn1;
    @FXML
    private TableColumn tableColumn2;
    @FXML
    private Button buttonProximo1;
    @FXML
    private AnchorPane anchorPaneCliente;
    @FXML
    private AnchorPane anchorPaneVeiculo;    
    @FXML
    private AnchorPane anchorPanePlano;
    @FXML
    private Button buttonNovo;

    // Dados de clientes
    @FXML
    private Label labelClienteId;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteCpf;
    @FXML
    private Label labelClienteRg;
    @FXML
    private Label labelClienteNascimento;
    @FXML
    private Label labelClienteCnh;
    @FXML
    private Label labelClienteVinculo;
    @FXML
    private Label labelClienteEmail;
    @FXML
    private Label labelEscolha;
    

    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        carregarTableViewCliente();
        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableView(newValue));
        FilteredList<Cliente> filteredData = new FilteredList<>(this.observableListClientes, p -> true);
        textFieldPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(cliente -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (cliente.getNome().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (cliente.getCpf().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
            tableView.setItems(filteredData);
        });
    }

    public void carregarTableViewCliente() {
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        listClientes = clienteDAO.listar();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tableView.setItems(observableListClientes);
    }
    
    private void alteraPasso(int passoAnterior, int novoPasso){
        if (novoPasso == 1){
            anchorPaneCliente.setVisible(false);
            anchorPanePlano.setVisible(false);
            anchorPaneVeiculo.setVisible(true);
            this.labelEscolha.setText("Escolha o Veículo");
            this.buttonNovo.setVisible(false);
        }
        passoAnterior = novoPasso;
        
    }

    public void selecionarItemTableView(Object selected) {
        if (this.passo == 0) {
            Cliente cliente = (Cliente)selected;
            if (selected != null) {
                labelClienteId.setText(String.valueOf(cliente.getIdCliente()));
                labelClienteNome.setText(cliente.getNome());
                labelClienteCpf.setText(cliente.getCpf());
                labelClienteRg.setText(cliente.getRg());
                labelClienteNascimento.setText(cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                labelClienteCnh.setText(cliente.getCnh());
                labelClienteVinculo.setText(cliente.getDataVinculo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                labelClienteEmail.setText(cliente.getEmail());
            } else {
                labelClienteId.setText("");
                labelClienteNome.setText("");
                labelClienteCpf.setText("");
                labelClienteRg.setText("");
                labelClienteNascimento.setText("");
                labelClienteCnh.setText("");
                labelClienteVinculo.setText("");
                labelClienteEmail.setText("");
            }
        }
    }

    public void handlebuttonProximo1(){
        Cliente cliente = null;
        if (!this.listClientes.isEmpty())
            cliente = (Cliente)tableView.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            locacao.setCliente(cliente);
            alteraPasso(this.passo, this.passo+1);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um gerente na Tabela!");
            alert.show();
        }
    }
}
