package projectmcm.controller.locador;

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
import projectmcm.model.domain.Funcionario;
import util.Seguranca;

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
    private Funcionario locador;

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

    public Funcionario getLocador() {
        return locador;
    }

    public void setLocador(Funcionario locador) {
        this.locador = locador;
        this.textFieldLocadorNome.setText(locador.getNome());
        this.textFieldLocadorEmail.setText(locador.getEmail());
        this.textFieldLocadorCpf.setText(locador.getCpf());
        this.textFieldLocadorRg.setText(locador.getRg());
        if (locador.getDataContratacao()!=null)
            this.datePickerLocadorDataContratacao.setValue(locador.getDataContratacao());
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {

            locador.setNome(textFieldLocadorNome.getText());
            locador.setEmail(textFieldLocadorEmail.getText());
            locador.setCpf(textFieldLocadorCpf.getText());
            locador.setRg(textFieldLocadorRg.getText());
            locador.setDataContratacao(datePickerLocadorDataContratacao.getValue());
            if(locador.getSenha() == null){
                locador.setSenha(Seguranca.geraSenhaPadrao(textFieldLocadorNome.getText(), "LPS"));
            }
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
        if (textFieldLocadorCpf.getText() == null || textFieldLocadorCpf.getText().length() == 0 || Seguranca.validarCpf(textFieldLocadorCpf.getText())) {
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
