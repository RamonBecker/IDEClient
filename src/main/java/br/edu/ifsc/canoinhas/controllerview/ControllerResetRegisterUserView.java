package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.modelDao.controller.threads.UpdateUsuarioServer;
import br.edu.ifsc.canoinhas.modelDao.controller.usuario.DaoDBUsuario;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerResetRegisterUserView implements Initializable {

	public static String viewRedefinirUser = "";

	@FXML
	private MenuItem menuItemBack;

	@FXML
	private Button btnConfirmar;

	@FXML
	private RadioButton radioSenha;

	@FXML
	private ToggleGroup group1;

	@FXML
	private RadioButton radioUsuario;

	@FXML
	private TextField txtUser;

	@FXML
	private TextField txtNewUser;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private PasswordField txtNewPassword;

	@FXML
	private RadioButton radioCadastrar;
	
    @FXML
    private RadioButton radioButtonExcluirConta;

    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		update();
		
	}
    
	public void alterUserPassword() {
		
		if (radioUsuario.isSelected()) {
			txtNewUser.setDisable(false);
			txtNewPassword.setDisable(true);
		}

		if (radioSenha.isSelected()) {
			txtNewUser.setDisable(true);
			txtNewPassword.setDisable(false);
		}

		if (radioCadastrar.isSelected()) {
			txtNewUser.setDisable(true);
			txtNewPassword.setDisable(true);
		}
		
		if(radioButtonExcluirConta.isSelected()) {
			txtNewUser.setDisable(true);
			txtNewPassword.setDisable(true);
		}

	}

	public void back() {

		FXMLLoader fxmlLoader = null;

		try {

			if (viewRedefinirUser.equals("IDE")) {
				fxmlLoader = new FXMLLoader(App.class.getResource("TelaIDEPrincipal.fxml"));
			}

			if (viewRedefinirUser.equals("ProgramPrincipal")) {
				fxmlLoader = new FXMLLoader(App.class.getResource("TelaPrincipalProgram.fxml"));
			}

			if (viewRedefinirUser.equals("Login")) {
				fxmlLoader = new FXMLLoader(App.class.getResource("TelaLogin.fxml"));
			}

			Stage myWin = (Stage) btnConfirmar.getScene().getWindow();

			Stage stage = new Stage();

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

	public void registerNewUserPassword() {

		DaoDBUsuario daoDBUsuario = DaoDBUsuario.getInstance();

		try {
			
			
			if(group1.getSelectedToggle() == null) {
				cleanFields();
				MessageAlert.mensagemErro(StringUtility.selectedUserPassword);
				return;
			}

			if (radioUsuario.isSelected()) {
				daoDBUsuario.submitEditNameUsuarioServer(txtUser.getText(), txtNewUser.getText(), txtPassword.getText(), "editName");
			}

			if (radioSenha.isSelected()) {
				
				daoDBUsuario.submitEditPasswordUsuarioServer(txtUser.getText(), txtPassword.getText(), txtNewPassword.getText(), "editSenha");
			}

			if (radioCadastrar.isSelected()) {
				daoDBUsuario.submitUsuarioServer(txtUser.getText(), txtPassword.getText(), "add");
			}
			
			if(radioButtonExcluirConta.isSelected()) {
				daoDBUsuario.submitRemoveUsuarioServer(txtUser.getText(), txtPassword.getText(), "remove");
			}
			cleanFields();
			update();

		} catch (IllegalArgumentException e) {
			MessageAlert.mensagemErro(e.getMessage());
		}

	}
	
	
	private void update() {
		try {
			Thread updateUsuario = new Thread(new UpdateUsuarioServer());
			updateUsuario.start();
			updateUsuario.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void cleanFields() {
		txtNewPassword.setText("");
		txtNewUser.setText("");
		txtPassword.setText("");
		txtUser.setText("");

		txtNewPassword.setDisable(false);
		txtNewUser.setDisable(false);
		txtPassword.setDisable(false);
		txtUser.setDisable(false);
		
		radioCadastrar.setSelected(false);
		radioSenha.setSelected(false);
		radioUsuario.setSelected(false);
	}


}
