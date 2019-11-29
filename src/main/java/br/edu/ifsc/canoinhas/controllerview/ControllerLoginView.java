package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;

import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.modelDao.controller.DaoDBUsuario;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLoginView {

	@FXML
	private TextField txtuserName;

	@FXML
	private Label lblEsqueceuSenha;

	@FXML
	private Button btnLogin;

	@FXML
	private PasswordField txtSenha;

	public void login() {

		DaoDBUsuario controllerDBUsuario = DaoDBUsuario.getInstance();

		try {
			if (controllerDBUsuario.login(txtuserName.getText().trim(), txtSenha.getText().trim())) {

				Stage myWin = (Stage) btnLogin.getScene().getWindow();

				Stage stage = new Stage();
				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaPrincipalProgram.fxml"));
				Parent root;

				root = (Parent) fxmlLoader.load();
				stage.setScene(new Scene(root));
				stage.show();
				myWin.close();
			} else {
				MessageAlert.mensagemErro(StringUtility.loginIncorret);
				cleanFields();
			}

		} catch (IOException e) {
			MessageAlert.mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void esqueceuSenha() {
		try {

			Stage myWin = (Stage) btnLogin.getScene().getWindow();

			Stage stage = new Stage();

			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaRedefinirSenhaUsuario.fxml"));
			Parent root;
			ControllerResetRegisterUserView.viewRedefinirUser = "Login";
			root = (Parent) fxmlLoader.load();

			stage.setScene(new Scene(root));
			stage.show();
			myWin.close();
			cleanFields();

		} catch (IOException e) {
			MessageAlert.mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	private void cleanFields() {
		txtSenha.setText("");
		txtuserName.setText("");
	}
}
