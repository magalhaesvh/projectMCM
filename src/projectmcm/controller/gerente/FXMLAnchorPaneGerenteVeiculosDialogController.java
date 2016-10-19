package projectmcm.controller.gerente;

import java.net.URL;
import java.sql.Connection;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projectmcm.model.dao.StatusDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Status;
import projectmcm.model.domain.Veiculo;

public class FXMLAnchorPaneGerenteVeiculosDialogController implements Initializable {

    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private TextField textFieldVeiculoCor;
    @FXML
    private TextField textFieldVeiculoValor;
    @FXML
    private TextField textFieldVeiculoPlaca;
    @FXML
    private TextField textFieldVeiculoChassi;
    @FXML
    private DatePicker datePickerVeiculoAnoModelo;
    @FXML
    private DatePicker datePickerVeiculoAnoFabricacao;
    @FXML
    private DatePicker datePickerVeiculoDataCompra;
    @FXML
    private TextField textFieldVeiculoObservacoes;
    @FXML
    private TextField textFieldVeiculoMotor;
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
    private ComboBox choiceBoxVeiculoStatus;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Veiculo veiculo;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final StatusDAO statusDAO = new StatusDAO();
    private ObservableList<Status> observableListStatus;
    private List<Status> listarStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusDAO.setConnection(connection);
        carregarComboBoxVeiculoStatus();
    }
    
    public void carregarComboBoxVeiculoStatus(){
        listarStatus = statusDAO.listar();
        observableListStatus = FXCollections.observableArrayList(listarStatus);
        choiceBoxVeiculoStatus.setItems(observableListStatus);
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.textFieldVeiculoCor.setText(veiculo.getCor());
        this.textFieldVeiculoValor.setText(String.valueOf(veiculo.getValor()));
        this.textFieldVeiculoPlaca.setText(veiculo.getPlaca());
        this.textFieldVeiculoChassi.setText(veiculo.getChassi());
        this.datePickerVeiculoAnoModelo.setValue(veiculo.getAnoModelo()==null?null:veiculo.getAnoModelo().toLocalDate());
        this.datePickerVeiculoAnoFabricacao.setValue(veiculo.getAnoFabricacao()==null?null:veiculo.getAnoFabricacao().toLocalDate());
        //this.datePickerVeiculoDataCompra.setValue(veiculo.getDataCompra()==null? null:veiculo.getDataCompra().toLocalDate());
        //this.textFieldVeiculoObservacoes.setText(veiculo.getObservacoes());
        this.textFieldVeiculoMotor.setText(String.valueOf(veiculo.getMotor()));
        //this.checkBoxVeiculoArCondicionado.setSelected(false);
        /*this.checkBoxVeiculoVidroEletrico.setSelected(veiculo.isVidroEletrico());
        this.checkBoxVeiculoTravaEletrica.setSelected(veiculo.isTravaEletrica());
        this.checkBoxVeiculoDirecaoEletrica.setSelected(veiculo.isDirecaoEletrica());
        this.checkBoxVeiculoCambioAutomatico.setSelected(veiculo.isCambioAutomatico());
        this.checkBoxVeiculoAbs.setSelected(veiculo.isAbs());
        this.checkBoxVeiculoAirBag.setSelected(veiculo.isAirBag());
        this.checkBoxVeiculoTracao4x4.setSelected(veiculo.isTracao4x4());
        this.choiceBoxVeiculoStatus.setValue(veiculo.getIdStatus());*/
    }

    @FXML
    public void handleButtonConfirmar() {

        //if (validarEntradaDeDados()) {
        veiculo.setCor(this.textFieldVeiculoCor.getText());
        veiculo.setValor(Float.parseFloat(this.textFieldVeiculoValor.getText()));
        veiculo.setPlaca(this.textFieldVeiculoPlaca.getText());
        veiculo.setChassi(this.textFieldVeiculoChassi.getText());
        //veiculo.setAnoModelo((java.sql.Date) Date.from(this.datePickerVeiculoAnoModelo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        //veiculo.setAnoFabricacao((java.sql.Date) Date.from(this.datePickerVeiculoAnoFabricacao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        //veiculo.setDataCompra((java.sql.Date) Date.from(this.datePickerVeiculoDataCompra.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        //veiculo.setObservacoes(this.textFieldVeiculoObservacoes.getText());
        veiculo.setMotor(Float.parseFloat(this.textFieldVeiculoMotor.getText()));
        veiculo.setArCondicionado(this.checkBoxVeiculoArCondicionado.isSelected());
        veiculo.setVidroEletrico(this.checkBoxVeiculoVidroEletrico.isSelected());
        veiculo.setTravaEletrica(this.checkBoxVeiculoTravaEletrica.isSelected());
        veiculo.setDirecaoEletrica(this.checkBoxVeiculoDirecaoEletrica.isSelected());
        veiculo.setCambioAutomatico(this.checkBoxVeiculoCambioAutomatico.isSelected());
        veiculo.setAbs(this.checkBoxVeiculoAbs.isSelected());
        veiculo.setAirBag(this.checkBoxVeiculoAirBag.isSelected());
        veiculo.setTracao4x4(this.checkBoxVeiculoTracao4x4.isSelected());
        //veiculo.setIdStatus(Status.valueOf(this.choiceBoxVeiculoStatus.getValue(), "id"));

        buttonConfirmarClicked = true;
        dialogStage.close();
        //}

    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    //Criar Validação
    //Validar entrada de dados para o cadastro
    /*private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldVeiculoMotor.getText() == null || textFieldVeiculoNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldVeiculoAnoM.getText() == null || textFieldVeiculoCpf.getText().length() == 0) {
            errorMessage += "Cpf inválido!\n";
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
     */
}
