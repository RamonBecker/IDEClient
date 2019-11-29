package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;

import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.entities.Endereco;
import br.edu.ifsc.canoinhas.entities.Ocorrencia;
import br.edu.ifsc.canoinhas.modelDao.controller.DaoDBOcorrencia;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerOcorrenciaView {
	@FXML
	private MenuItem back;

	@FXML
	private TextField txtRua;

	@FXML
	private TextField txtTelefone;

	@FXML
	private RadioButton radioLeve;

	@FXML
	private ToggleGroup group1;

	@FXML
	private RadioButton radioMedio;

	@FXML
	private RadioButton radioAlta;

	@FXML
	private TextField txtNomeVitima;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtComplemento;

	@FXML
	private Button btnDeslocarViatura;

	@FXML
	private TextField txtCidade;

	@FXML
	private TextField txtCEP;

	private String gravidade;

	private DaoDBOcorrencia controllerDBOcorrencia = DaoDBOcorrencia.getInstance();

	public void back() {

		try {
			Stage myWin = (Stage) btnDeslocarViatura.getScene().getWindow();

			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaPrincipalProgram.fxml"));
			Parent root;

			root = (Parent) fxmlLoader.load();

			stage.setScene(new Scene(root));
			stage.show();
			myWin.close();

		} catch (IOException e) {
			MessageAlert.mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void atenderOcorrencia() {

		if (!radioButtonSelected()) {
			return;
		}

		try {

			Endereco endereco = new Endereco(txtRua.getText().trim(), txtBairro.getText().trim(),txtNumero.getText().trim(), txtTelefone.getText().trim(), txtComplemento.getText().trim(),
					txtCEP.getText().trim(), txtCidade.getText().trim());
			
			//Endereco endereco1 = new Endereco(rua, bairro, numero, telefone, complemento, cep, cidade)

			Ocorrencia ocorrencia = new Ocorrencia(txtNomeVitima.getText().trim(), gravidade, endereco);

			controllerDBOcorrencia.addOcorrencia(ocorrencia);

			MessageAlert.mensagemRealizadoSucesso(StringUtility.viaturaDeslocada);

			cleanFields();

		} catch (IllegalArgumentException e) {
			System.out.println(e.getLocalizedMessage());
			MessageAlert.mensagemErro(e.getMessage());
		}
	}

	private void cleanFields() {
		txtBairro.setText("");
		txtComplemento.setText("");
		txtNomeVitima.setText("");
		txtNumero.setText("");
		txtRua.setText("");
		txtTelefone.setText("");
		txtCEP.setText("");
		txtCidade.setText("");

		radioAlta.setSelected(false);
		radioLeve.setSelected(false);
		radioMedio.setSelected(false);
	}

	private boolean radioButtonSelected() {

		if (group1.getSelectedToggle() == null) {
			MessageAlert.mensagemErro(StringUtility.selectedNullGravidadeOcorrencia);
			return false;
		}

		if (radioAlta.isSelected()) {
			gravidade = radioAlta.getText();
		}
		if (radioLeve.isSelected()) {
			gravidade = radioLeve.getText();
		}
		if (radioMedio.isSelected()) {
			gravidade = radioMedio.getText();
		}

		return true;
	}
}
