/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller;

import java.net.URL;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import projectmcm.model.dao.ClienteDAO;
import projectmcm.model.dao.PlanoDAO;
import projectmcm.model.dao.VeiculoDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Cliente;
import projectmcm.model.domain.Locacao;
import projectmcm.model.domain.Plano;
import projectmcm.model.domain.Veiculo;

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

    // Dados de Veiculos
    @FXML
    private Label labelGerenteVeiculoId;
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
    private Label labelGerenteVeiculoMarca;
    @FXML
    private Label labelGerenteVeiculoModelo;
    @FXML
    private Label labelGerenteVeiculoStatus;

    // Dados plano
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
    private CheckBox checkBoxDiaria;
    @FXML
    private CheckBox checkBoxPlanoCustoFixo;

    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    private List<Veiculo> listVeiculos;
    private ObservableList<Veiculo> observableListVeiculos;
    private List<Plano> listPlanos;
    private ObservableList<Plano> observableListPlanos;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private final PlanoDAO planoDAO = new PlanoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        veiculoDAO.setConnection(connection);
        planoDAO.setConnection(connection);
        carregarTableViewCliente();
        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableView(newValue));
    }

    public void carregarTableViewCliente() {
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        listClientes = clienteDAO.listar();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tableView.setItems(observableListClientes);
    }

    public void carregarTableViewVeiculos() {
        tableColumn1.setText("Marca");
        tableColumn2.setText("Modelo");
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        listVeiculos = veiculoDAO.listar();
        observableListVeiculos = FXCollections.observableArrayList(listVeiculos);
        tableView.setItems(observableListVeiculos);
    }
    
    public void carregarTableViewPlanos() {
        tableColumn1.setText("Plano");
        tableColumn2.setText("Descrição");
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        listPlanos = planoDAO.listar();
        observableListPlanos = FXCollections.observableArrayList(listPlanos);
        tableView.setItems(observableListPlanos);
    }

    private void alteraPasso(int passoAnterior, int novoPasso) {
        if (novoPasso == 1) {
            anchorPaneCliente.setVisible(false);
            anchorPanePlano.setVisible(false);
            anchorPaneVeiculo.setVisible(true);
            this.labelEscolha.setText("Escolha o Veículo");
            carregarTableViewVeiculos();
        } else if (novoPasso == 2) {
            anchorPaneCliente.setVisible(false);
            anchorPanePlano.setVisible(true);
            anchorPaneVeiculo.setVisible(false);
            this.labelEscolha.setText("Escolha o Plano");
            carregarTableViewPlanos();
        }
        this.passo = novoPasso;

    }

    public void selecionarItemTableView(Object selected) {
        if (this.passo == 0) {
            Cliente cliente = (Cliente) selected;
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
        } else if (this.passo == 1) {
            Veiculo veiculo = (Veiculo) selected;
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
                this.checkBoxVeiculoArCondicionado.setSelected(veiculo.isArCondicionado());
                this.checkBoxVeiculoVidroEletrico.setSelected(veiculo.isVidroEletrico());
                this.checkBoxVeiculoTravaEletrica.setSelected(veiculo.isTravaEletrica());
                this.checkBoxVeiculoDirecaoEletrica.setSelected(veiculo.isDirecaoEletrica());
                this.checkBoxVeiculoCambioAutomatico.setSelected(veiculo.isCambioAutomatico());
                this.checkBoxVeiculoAbs.setSelected(veiculo.isAbs());
                this.checkBoxVeiculoAirBag.setSelected(veiculo.isAirBag());
                this.checkBoxVeiculoTracao4x4.setSelected(veiculo.isTracao4x4());
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
                this.checkBoxVeiculoArCondicionado.setSelected(false);
                this.checkBoxVeiculoVidroEletrico.setSelected(false);
                this.checkBoxVeiculoTravaEletrica.setSelected(false);
                this.checkBoxVeiculoDirecaoEletrica.setSelected(false);
                this.checkBoxVeiculoCambioAutomatico.setSelected(false);
                this.checkBoxVeiculoAbs.setSelected(false);
                this.checkBoxVeiculoAirBag.setSelected(false);
                this.checkBoxVeiculoTracao4x4.setSelected(false);
                this.labelGerenteVeiculoMarca.setText("");
                this.labelGerenteVeiculoModelo.setText("");
                this.labelGerenteVeiculoStatus.setText("");
            }
        } else if (this.passo == 2) {
            Plano plano = (Plano) selected;
            if (plano != null) {
                this.labelPlanoId.setText(String.valueOf(plano.getIdPlano()));
                this.labelPlanoNome.setText(plano.getNome());
                this.labelPlanoRegulamento.setText(plano.getRegulamento());
                this.labelPlanoDescricao.setText(plano.getDescricao());
                this.labelPlanoQuilometragem.setText(String.valueOf(plano.getValorQuilometragem()));
                this.labelPlanoCustoFixo.setText(String.valueOf(plano.getValorCusto()));
                this.labelPlanoDiaria.setText(String.valueOf(plano.getValorDiaria()));
                this.checkBoxPlanoQuilometragem.setSelected(plano.isCalculoQuilometragem());
                this.checkBoxDiaria.setSelected(plano.isDiaria());
                this.checkBoxPlanoCustoFixo.setSelected(plano.isCustoFixo());
            } else {
                this.labelPlanoId.setText("");
                this.labelPlanoNome.setText("");
                this.labelPlanoRegulamento.setText("");
                this.labelPlanoDescricao.setText("");
                this.labelPlanoQuilometragem.setText("");
                this.labelPlanoCustoFixo.setText("");
                this.labelPlanoDiaria.setText("");
            }
        }
    }

    public void handlebuttonProximo1() {
        Cliente cliente = null;
        if (!this.listClientes.isEmpty()) {
            cliente = (Cliente) tableView.getSelectionModel().getSelectedItem();
        }
        if (cliente != null) {
            locacao.setCliente(cliente);
            alteraPasso(this.passo, this.passo + 1);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na tabela!");
            alert.show();
        }
    }

    public void handlebuttonProximo2() {
        Veiculo veiculo = null;
        if (!this.listVeiculos.isEmpty()) {
            veiculo = (Veiculo) tableView.getSelectionModel().getSelectedItem();
        }
        if (veiculo != null) {
            locacao.setVeiculo(veiculo);
            alteraPasso(this.passo, this.passo + 1);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um veículo na tabela!");
            alert.show();
        }
    }
    
    public void handlebuttonFinalizar(){
        if (this.locacao.getCliente() != null && this.locacao.getPlano() != null && this.locacao.getVeiculo() != null){
            
        }
    }
}
