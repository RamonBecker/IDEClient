package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.modelDao.controller.threads.UpdateUsuarioServer;
import br.edu.ifsc.canoinhas.modelDao.controller.usuario.DaoDBUsuario;
import br.edu.ifsc.canoinhas.utility.ControllerReferenceIDE;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLoginView implements Initializable {

	@FXML
	private TextField txtuserName;

	@FXML
	private Label lblEsqueceuSenha;

	@FXML
	private Button btnLogin;

	@FXML
	private PasswordField txtSenha;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		update();
	}

	private void update() {

		try {
			Thread updateThread = new Thread(new UpdateUsuarioServer());
			updateThread.start();
			updateThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void login() {

		ControllerReferenceIDE controllerReferenceIDE = ControllerReferenceIDE.getInstace();
		controllerReferenceIDE.setControllerLogin(this);

		ControllerLoadingView.view = "Logar";

		FXMLLoader fxmlLoaderteste = null;

		fxmlLoaderteste = new FXMLLoader(App.class.getResource("TelaCarregamento.fxml"));

		Parent parent;
		try {
			parent = fxmlLoaderteste.load();
			Scene newScene = new Scene(parent);
			Stage newStage = new Stage();
			newStage.setScene(newScene);
			newStage.show();
		} catch (IOException e2) {
			e2.printStackTrace();
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

	public void cleanFields() {
		txtSenha.setText("");
		txtuserName.setText("");
	}

	public Button getBtnLogin() {
		return btnLogin;
	}

	public TextField getTxtuserName() {
		return txtuserName;
	}

	public PasswordField getTxtSenha() {
		return txtSenha;
	}
	
	

}
