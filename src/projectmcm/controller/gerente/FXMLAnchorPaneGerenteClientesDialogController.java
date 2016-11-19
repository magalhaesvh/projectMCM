/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller.gerente;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projectmcm.model.domain.Cliente;

/**
 * FXML Controller class
 *
 * @author vh_ma
 */
public class FXMLAnchorPaneGerenteClientesDialogController implements Initializable {

    @FXML
    private TextField textFieldClienteNome;
    @FXML
    private TextField textFieldClienteEmail;
    @FXML
    private TextField textFieldClienteCpf;
    @FXML
    private TextField textFieldClienteRg;
    @FXML
    private TextField textFieldClienteCnh;
    @FXML
    private DatePicker datePickerClienteNascimento;
    @FXML
    private DatePicker datePickerClienteVinculo;
    @FXML
    private DatePicker textFieldClienteEndereco;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.textFieldClienteNome.setText(cliente.getNome());
        this.textFieldClienteEmail.setText(cliente.getEmail());
        this.textFieldClienteCpf.setText(cliente.getCpf());
        this.textFieldClienteRg.setText(cliente.getRg());
        this.datePickerClienteNascimento.setValue(cliente.getDataNascimento()==null?null:cliente.getDataNascimento().toLocalDate());
        this.datePickerClienteVinculo.setValue(cliente.getDataVinculo()==null?null:cliente.getDataVinculo().toLocalDate());
        ///this.textFieldClienteEndereco.getValue(cliente.getEndereco()==null?null:cliente.getEndereco().getIdEndereco());
        //????????????????
       
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {

            cliente.setNome(textFieldClienteNome.getText());
            cliente.setEmail(textFieldClienteEmail.getText());
            cliente.setCpf(textFieldClienteCpf.getText());
            cliente.setRg(textFieldClienteRg.getText());
            //nao sei pegar datas

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

        if (textFieldClienteNome.getText() == null || textFieldClienteNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldClienteCpf.getText() == null || textFieldClienteCpf.getText().length() == 0) {
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
