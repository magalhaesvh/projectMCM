package projectmcm.controller.gerente;

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
import projectmcm.model.domain.Locador;

public class FXMLAnchorPaneGerenteLocadoresDialogController implements Initializable {

    @FXML
    private TextField textFieldLocadorNome;
    @FXML
    private TextField textFieldLocadorEmail;
    @FXML
    private TextField textFieldLocadorCpf;
    @FXML
    private TextField textFieldLocadorRg;
    @FXML
    private DatePicker datePickerLocadorDataContratacao;
    @FXML
    private TextField textFieldLocadorSenha;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Locador gerente;

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

    public Locador getLocador() {
        return gerente;
    }

    public void setLocador(Locador gerente) {
        this.gerente = gerente;
        this.textFieldLocadorNome.setText(gerente.getNome());
        this.textFieldLocadorEmail.setText(gerente.getEmail());
        this.textFieldLocadorCpf.setText(gerente.getCpf());
        this.textFieldLocadorRg.setText(gerente.getRg());
        //System.out.println(this.datePickerLocadorData_contratacao.getValue());
        //this.datePickerLocadorData_contratacao.setValue(null);
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {

            gerente.setNome(textFieldLocadorNome.getText());
            gerente.setEmail(textFieldLocadorEmail.getText());
            gerente.setCpf(textFieldLocadorCpf.getText());
            gerente.setRg(textFieldLocadorRg.getText());
            //gerente.setData_contratacao((java.sql.Date) Date.from(datePickerLocadorData_contratacao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            //gerente.setSenha(textFieldLocadorSenha.getText());

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

        if (textFieldLocadorNome.getText() == null || textFieldLocadorNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldLocadorCpf.getText() == null || textFieldLocadorCpf.getText().length() == 0) {
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
