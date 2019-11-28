package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.db.connection.controller.projeto.DaoDBProjeto;
import br.edu.ifsc.canoinhas.entities.Projeto;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerProjetoView implements Initializable {

	@FXML
	private TextField txtNameProject;

	@FXML
	private Button btnConfirm;

	@FXML
	private RadioButton radioBTYes;

	@FXML
	private ToggleGroup group;

	@FXML
	private RadioButton radioBTNo;

	@FXML
	private TextField txtLocalProject;

	@FXML
	private ComboBox<String> comboBoxJRE;

	@FXML
	private Button btnNext;

	private DaoDBProjeto controllerDBProjeto;

	public void createProject() {

		if (group.getSelectedToggle() == null) {
			MessageAlert.mensagemErro(StringUtility.noSelectedLocation);
			return;
		}

		controllerDBProjeto = DaoDBProjeto.getInstance();

		controllerDBProjeto.submitProjetoAddServer(txtNameProject.getText(), txtLocalProject.getText(), "add");

		try {
			controllerDBProjeto.getAllProjeto();
		} catch (IOException e) {
			MessageAlert.mensagemErro("Erro ao receber os dados dos projetos: " + e.getMessage());
			e.printStackTrace();
		}

		txtNameProject.setText("");
		txtLocalProject.setText("local\\local\\local");
		txtLocalProject.setDisable(true);
		group.getSelectedToggle().setSelected(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> listJRE = FXCollections.observableArrayList();

		listJRE.add("JavaSE-12");
		listJRE.add("Java-SE-11");
		listJRE.add("Java-SE-10");
		listJRE.add("Java-SE-9");
		listJRE.add("Java-SE-8");

		comboBoxJRE.setItems(listJRE);

		comboBoxJRE.setPromptText("Selecione a JRE");

	}

	public void local() {
		if (radioBTYes.isSelected()) {
			txtLocalProject.setDisable(false);
			txtLocalProject.setEditable(true);
			txtLocalProject.setPromptText("");
		}
		if (radioBTNo.isSelected()) {
			txtLocalProject.setDisable(true);
			txtLocalProject.setEditable(false);
			txtLocalProject.setText(StringUtility.textLocalPrompt);
			txtLocalProject.setPromptText(StringUtility.textLocalPrompt);
		}
	}

	@FXML
	public void nextView() {

		try {

			Stage myStage = (Stage) btnConfirm.getScene().getWindow();
			Stage stage = new Stage();

			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaCriacaoPackage.fxml"));

			Parent root = (Parent) fxmlLoader.load();

			stage.setScene(new Scene(root));
			stage.show();
			myStage.close();

		} catch (IOException e) {
			MessageAlert.mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
}
