package projectmcm.controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projectmcm.model.domain.Gerente;

public class FXMLAnchorPaneAdminGerentesDialogController implements Initializable {

    @FXML
    private TextField textFieldGerenteNome;
    @FXML
    private TextField textFieldGerenteCnpj;
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

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Gerente gerente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
        this.textFieldGerenteNome.setText(gerente.getNome());
        this.textFieldGerenteEmail.setText(gerente.getEmail());
        this.textFieldGerenteCpf.setText(gerente.getCpf());
        this.textFieldGerenteRg.setText(gerente.getRg());
        //System.out.println(this.datePickerGerenteData_contratacao.getValue());
        //this.datePickerGerenteData_contratacao.setValue(null);
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {

            gerente.setNome(textFieldGerenteNome.getText());
            gerente.setEmail(textFieldGerenteEmail.getText());
            gerente.setCpf(textFieldGerenteCpf.getText());
            gerente.setRg(textFieldGerenteRg.getText());
            //gerente.setData_contratacao((java.sql.Date) Date.from(datePickerGerenteData_contratacao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            //gerente.setSenha(textFieldGerenteSenha.getText());

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
        if (textFieldGerenteCpf.getText() == null || textFieldGerenteCpf.getText().length() == 0) {
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



}
