package projectmcm.controller.admin;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projectmcm.model.dao.StatusDAO;
import projectmcm.model.database.Database;
import projectmcm.model.database.DatabaseFactory;
import projectmcm.model.domain.Status;

public class FXMLAnchorPaneAdminSituacoesDialogController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*
    @FXML
    private TextField textFieldSituacaoNome;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Label labelTitleAdminSituacao;
    @FXML
    private ComboBox comboBoxSituacaoTipo;

    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final StatusDAO statusDAO = new StatusDAO();
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Status status;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Veículo", 1);
        map.put("Vistoria", 2);
        ObservableList teste = FXCollections.observableArrayList(map);
        comboBoxSituacaoTipo.setItems(teste);
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setLabelTitleAdminSituacao(String title){
        this.labelTitleAdminSituacao.setText(title);
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

    public Status getSituacao() {
        return status;
    }

    public void setSituacao(Status status) {
        this.status = status;
        this.textFieldSituacaoNome.setText(status.getNome());
        this.comboBoxSituacaoTipo.setValue(status.getTipo());
    }

    @FXML
    public void handleButtonConfirmar() {
        System.out.println("");
        if (validarEntradaDeDados()) {

            status.setNome(textFieldSituacaoNome.getText());
            status.setTipo(comboBoxSituacaoTipo.getValue());
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

        if (textFieldSituacaoNome.getText() == null || textFieldSituacaoNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (comboBoxSituacaoTipo.getValue() == null) {
            errorMessage += "Tipo inválido!\n";
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
