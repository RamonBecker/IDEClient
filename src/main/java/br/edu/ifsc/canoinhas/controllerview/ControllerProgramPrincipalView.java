package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;

import br.edu.ifsc.canoinhas.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class ControllerProgramPrincipalView {
	@FXML
	private MenuItem menuItemLogoff;

	@FXML
	private MenuItem menuItemUserPassword;

	@FXML
	private Button btnAtenderOcorrencia;

	@FXML
	private Button btnCadastrarEmpresa;
	
    @FXML
    private Button btnMostrarOcorrencias;
    
    
    public void mostrarOcorrencias() {
    	try {

			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaOcorrencia.fxml"));
			Parent root;
			root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));

			close();
			stage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    
    

	public void atenderOcorrencia() {
		try {

			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaAtenderOcorrencia.fxml"));
			Parent root;
			root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));

			close();
			stage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void cadastrarEmpresa() {
		try {

			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaEmpresa.fxml"));
			Parent root;
			root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));

			close();
			stage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	public void logoff() {
		close();
	}

	@FXML
	public void resetUserPassword() {
		try {

			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaRedefinirSenhaUsuario.fxml"));
			Parent root;
			ControllerResetRegisterUserView.viewRedefinirUser = "ProgramPrincipal";
			root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));

			close();
			stage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void close() {
		Stage myStage = (Stage) btnAtenderOcorrencia.getScene().getWindow();
		myStage.close();
	}
}
