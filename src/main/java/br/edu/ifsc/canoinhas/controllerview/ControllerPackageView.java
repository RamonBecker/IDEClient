package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.db.connection.controller.ControllerDBProjeto;
import br.edu.ifsc.canoinhas.entities.Projeto;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerPackageView implements Initializable {
	@FXML
	private Button btnNext;

	@FXML
	private TextField txtFolderProject;

	@FXML
	private TextField txtNamePackage;

	@FXML
	private TextField txtNameProject;

	@FXML
	private Button btnCreatePackage;

	@FXML
	private TableView<Projeto> tableViewProject;

	@FXML
	private TableColumn<Projeto, String> tableColumnProject;

	private Projeto projeto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		ControllerDBProjeto controllerDBProjeto = ControllerDBProjeto.getInstance();
//
//		ObservableList<Projeto> listProjeto = FXCollections.observableArrayList();
//
//		listProjeto.addAll(controllerDBProjeto.getListProjeto());
//
//		tableColumnProject.setCellValueFactory(new PropertyValueFactory<Projeto, String>("nome"));
//
//		tableViewProject.setItems(listProjeto);
	}

	public void tableAction() {

//		projeto = tableViewProject.getSelectionModel().getSelectedItem();
//
//		txtNameProject.setText(projeto.getNome());
//
//		txtFolderProject.setText(projeto.getLocation());
	}

	public void createPackage() {

//		ControllerDBProjeto controllerDBProjeto = ControllerDBProjeto.getInstance();
//
//		controllerDBProjeto.addPackageToProject(projeto, txtNamePackage.getText().trim());
//
//		cleanFields();

	}

	private void cleanFields() {
		txtNameProject.setText("");
		txtNamePackage.setText("");
		txtFolderProject.setText("");
	}

	public void nextViewClass() {

		try {
			
			Stage myStage = (Stage) btnCreatePackage.getScene().getWindow();

			Stage stage = new Stage();

			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaCriacaoClasse.fxml"));

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