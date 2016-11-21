/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.controller.gerente;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projectmcm.model.domain.Plano;

/**
 * FXML Controller class
 *
 * @author vh_ma
 */
public class FXMLAnchorPaneGerentePlanosDialogController implements Initializable {

    @FXML
    private TextField textFieldPlanoNome;
    @FXML
    private TextField textFieldPlanoId;
    @FXML
    private TextField textFieldPlanoRegulamento;
    @FXML
    private TextField textFieldPlanoDescricao;
    @FXML
    private TextField textFieldPlanoQuilometragem;
    @FXML
    private TextField textFieldPlanoCustoFixo;
    @FXML
    private TextField textFieldPlanoDiaria;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private CheckBox checkBoxPlanoQuilometragem;
    @FXML
    private CheckBox checkBoxPlanoCustoFixo;
    @FXML
    private CheckBox checkBoxPlanoDiaria;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Plano plano;

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

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
        this.textFieldPlanoNome.setText(plano.getNome());
        this.textFieldPlanoRegulamento.setText(plano.getRegulamento());
        this.textFieldPlanoDescricao.setText(plano.getDescricao());
        this.textFieldPlanoQuilometragem.setText(String.valueOf(plano.getValorQuilometragem()));
        this.textFieldPlanoCustoFixo.setText(String.valueOf(plano.getValorCusto()));
        this.textFieldPlanoDiaria.setText(String.valueOf(plano.getValorDiaria()));
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {

            plano.setNome(textFieldPlanoNome.getText());
            plano.setRegulamento(textFieldPlanoRegulamento.getText());
            plano.setDescricao(textFieldPlanoDescricao.getText());
            if (this.checkBoxPlanoQuilometragem.isSelected()) {
                plano.setValorQuilometragem(Float.parseFloat(this.textFieldPlanoQuilometragem.getText()));
                plano.setValorCusto(0);
                plano.setValorDiaria(0);
                plano.setIdTipo(1);
            } else if (this.checkBoxPlanoCustoFixo.isSelected()) {
                plano.setValorCusto(Float.parseFloat(this.textFieldPlanoCustoFixo.getText()));
                plano.setValorDiaria(0);
                plano.setValorQuilometragem(0);
                plano.setIdTipo(2);
            } else if (this.checkBoxPlanoDiaria.isSelected()) {
                plano.setValorDiaria(Float.parseFloat(this.textFieldPlanoDiaria.getText()));
                plano.setValorCusto(0);
                plano.setValorQuilometragem(0);
                plano.setIdTipo(3);
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

        if (textFieldPlanoNome.getText() == null || textFieldPlanoNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
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
