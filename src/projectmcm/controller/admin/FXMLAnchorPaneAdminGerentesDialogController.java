package projectmcm.controller.admin;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projectmcm.model.dao.AgenciaDAO;
import projectmcm.model.dao.FuncionarioDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Agencia;
import projectmcm.model.domain.Funcionario;
import util.Seguranca;

public class FXMLAnchorPaneAdminGerentesDialogController implements Initializable {

    @FXML
    private TextField textFieldGerenteNome;
    @FXML
    private TextField textFieldGerenteEmail;
    @FXML
    private TextField textFieldGerenteCpf;
    @FXML
    private TextField textFieldGerenteRg;
    @FXML
    private DatePicker datePickerGerenteDataContratacao;
    @FXML
    private TextField textFieldGerenteSenha;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Label labelTitleAdminGerente;
    @FXML
    private ComboBox comboBoxGerenteAgencia;

    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final FuncionarioDAO gerenteDAO = new FuncionarioDAO();
    private final AgenciaDAO agenciaDAO = new AgenciaDAO();
    private ObservableList<Agencia> observableListAgencia;
    private List<Agencia> listarAgencias;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Funcionario gerente;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agenciaDAO.setConnection(connection);
        carregarComboBoxGerenteAgencia();
        if (listarAgencias.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não existem agência para se ter gerente! Cadastre uma agência primeiramente.");
            alert.show();
            dialogStage.close();            
        }   
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setLabelTitleAdminGerente(String title){
        this.labelTitleAdminGerente.setText(title);
    }
    
    public void carregarComboBoxGerenteAgencia(){
        listarAgencias = agenciaDAO.listar();
        observableListAgencia = FXCollections.observableArrayList(listarAgencias);
        comboBoxGerenteAgencia.setItems(observableListAgencia);
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }
    
    public void setTextButtonConfirmar(String texto) {
        this.buttonConfirmar.setText(texto);
    }

    public Funcionario getGerente() {
        return gerente;
    }

    public void setGerente(Funcionario gerente) {
        this.gerente = gerente;
        this.textFieldGerenteNome.setText(gerente.getNome());
        this.textFieldGerenteEmail.setText(gerente.getEmail());
        this.textFieldGerenteCpf.setText(gerente.getCpf());
        this.textFieldGerenteRg.setText(gerente.getRg());
        if (gerente.getDataContratacao()!=null)
            this.datePickerGerenteDataContratacao.setValue(gerente.getDataContratacao());
        this.comboBoxGerenteAgencia.setValue(gerente.getAgencia());
    }

    @FXML
    public void handleButtonConfirmar() {
        System.out.println("");
        if (validarEntradaDeDados()) {

            gerente.setNome(textFieldGerenteNome.getText());
            gerente.setEmail(textFieldGerenteEmail.getText());
            gerente.setCpf(textFieldGerenteCpf.getText());
            gerente.setRg(textFieldGerenteRg.getText());
            gerente.setDataContratacao(datePickerGerenteDataContratacao.getValue());
            gerente.setTipo((byte)2);
            if(gerente.getSenha() == null){
                gerente.setSenha(Seguranca.geraSenhaPadrao(textFieldGerenteNome.getText(), datePickerGerenteDataContratacao.getValue().getYear()));
            }
            gerente.setAgencia((Agencia) comboBoxGerenteAgencia.getValue());
            buttonConfirmarClicked = true;
            dialogStage.close();
        }

    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldGerenteNome.getText() == null || textFieldGerenteNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldGerenteCpf.getText() == null || textFieldGerenteCpf.getText().length() == 0 || !Seguranca.validarCpf(textFieldGerenteCpf.getText())) {
            errorMessage += "Cpf inválido!\n";
        }
        if (textFieldGerenteEmail.getText() == null || textFieldGerenteEmail.getText().length() == 0 || !Seguranca.validarEmail(textFieldGerenteEmail.getText())) {
            errorMessage += "E-mail inválido!\n";
        }
        if (textFieldGerenteRg.getText() == null || textFieldGerenteRg.getText().length() == 0 || !Seguranca.validarRg(textFieldGerenteRg.getText())) {
            errorMessage += "RG inválido!\n";
        }
        if (datePickerGerenteDataContratacao.getValue() == null) {
            errorMessage += "Data de contratação inválida!\n";
        }
        if (comboBoxGerenteAgencia.getValue() == null) {
            errorMessage += "Agência inválida!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }



}
