package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.entities.Classe;
import br.edu.ifsc.canoinhas.entities.Pacote;
import br.edu.ifsc.canoinhas.entities.Projeto;
import br.edu.ifsc.canoinhas.modelDao.controller.projeto.DaoDBClasse;
import br.edu.ifsc.canoinhas.modelDao.controller.projeto.DaoDBProjeto;
import br.edu.ifsc.canoinhas.modelDao.controller.projeto.UpdateView;
import br.edu.ifsc.canoinhas.modelDao.controller.threads.UpdateProjetoServer;
import br.edu.ifsc.canoinhas.utility.StringUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerIDEView implements Initializable {
	@FXML
	private Button btnClear;

	@FXML
	private Button btnRun;

	@FXML
	private Button btnUpdateProject;

	@FXML
	private Button btnBackPackage;

	@FXML
	private Button btnBackProject;

	@FXML
	private MenuItem menuItemClose;

	@FXML
	private MenuItem menuItemCreateProject;

	@FXML
	private MenuItem menuItemCreatePackage;

	@FXML
	private MenuItem menuItemCreateClass;

	@FXML
	private MenuItem menuItemEdit;

	@FXML
	private TextArea textAreaProgram;

	@FXML
	private TextField textNameProject;

	@FXML
	private TextField textNamePackage;

	@FXML
	private TextArea textAreaConsole;

	@FXML
	private ListView<Projeto> listProject;

	@FXML
	private ListView<Pacote> listViewPackage;

	@FXML
	private ListView<Classe> listViewClass;

	@FXML
	private TableView<Projeto> tableProjeto;

	@FXML
	private TableColumn<Projeto, String> columnProjeto;

	@FXML
	private TableView<Pacote> tablePacote;

	@FXML
	private TableColumn<Pacote, String> columnPacote;

	@FXML
	private TableView<Classe> tableClasse;

	@FXML
	private TableColumn<Classe, String> columnClasse;

	private Projeto projeto;

	private Pacote pacote;

	private Classe classe;

	private DaoDBProjeto daoDBProjeto;

	public void createNewProject(ActionEvent e) {
		try {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaCriacaoProjeto.fxml"));
			Parent root;
			UpdateView update = UpdateView.getInstance();
			update.setControllerIDEView(this);
			root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();
			

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void close() {
		Stage stage = (Stage) btnRun.getScene().getWindow();
		stage.close();
	}

	public void createNewClass(ActionEvent e) {
		try {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaCriacaoClasse.fxml"));
			Parent root;

			root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void createNewPackage(ActionEvent e) {

		try {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaCriacaoPackage.fxml"));
			Parent root;

			root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void updateProject() {

		daoDBProjeto = DaoDBProjeto.getInstance();

		columnProjeto.setCellValueFactory(new PropertyValueFactory<Projeto, String>("nome"));

		ObservableList<Projeto> listProjeto = FXCollections.observableArrayList();

		listProjeto.addAll(daoDBProjeto.getListProjeto());

		tableProjeto.setItems(listProjeto);

	}

	public void actionTableProject() {

		projeto = tableProjeto.getSelectionModel().getSelectedItem();

		columnPacote.setCellValueFactory(new PropertyValueFactory<Pacote, String>("nome"));

		ObservableList<Pacote> listPacote = FXCollections.observableArrayList();

		listPacote.addAll(projeto.getListPacote());

		tablePacote.setItems(listPacote);

		tableProjeto.setVisible(false);

		tablePacote.setVisible(true);
	}

	public void actionTablePackage() {

		pacote = tablePacote.getSelectionModel().getSelectedItem();

		ObservableList<Classe> listClasse = FXCollections.observableArrayList();

		listClasse.addAll(pacote.getListClasse());

		columnClasse.setCellValueFactory(new PropertyValueFactory<Classe, String>("nome"));

		tableClasse.setItems(listClasse);

		tableProjeto.setVisible(false);

		tablePacote.setVisible(false);

		tableClasse.setVisible(true);

		btnBackPackage.setDisable(false);
		for (Classe classe : pacote.getListClasse()) {

			classe.setCodigoClasse(classe.getMain(), classe.getTypeClasse());
			try {
				new DaoDBClasse().submitCodigoEditClasseServer(String.valueOf(classe.getId()), classe.getCodigo(),
						"editCodigo");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void getClassCodigo() {

		classe = tableClasse.getSelectionModel().getSelectedItem();
		textAreaProgram.setText(classe.getCodigo());
	}

	public void backProject() {

		tablePacote.setVisible(false);

		tableClasse.setVisible(false);

		tableProjeto.setVisible(true);
		btnBackPackage.setDisable(true);
		textAreaConsole.setText("");
		textAreaProgram.setText("");
	}

	public void backPackage() {

		tableClasse.setVisible(false);
		tablePacote.setVisible(true);
		textAreaConsole.setText("");
		textAreaProgram.setText("");

	}

	public void runClass() {

		if (textAreaProgram.getText().contains("Ola mundo")) {
			textAreaConsole.setText(StringUtility.olaMundo);
		}

		if (textAreaProgram.getText().contains("lauch()")) {

			try {
				Stage stage = new Stage();
				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaLogin.fxml"));
				Parent root;
				textAreaConsole.setText(StringUtility.running);
				root = (Parent) fxmlLoader.load();
				stage.setScene(new Scene(root));
				stage.show();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		daoDBProjeto = DaoDBProjeto.getInstance();
		classe.setCodigo(textAreaProgram.getText());

		try {
			new DaoDBClasse().submitCodigoEditClasseServer(String.valueOf(classe.getId()), classe.getCodigo(),
					"editCodigo");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clean() {
		textAreaConsole.setText("");
	}

	public void textArea(String text) {
		textAreaConsole.setText(text);

	}

	public void registerUser() {
		try {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaRedefinirSenhaUsuario.fxml"));
			Parent root;
			ControllerResetRegisterUserView.viewRedefinirUser = "IDE";
			root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void editProject() {
		try {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TelaEditProject.fxml"));
			Parent root;
			root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		daoDBProjeto = DaoDBProjeto.getInstance();
		updateProjectDB();
		daoDBProjeto.processFirstProjeto();
		updateProject();
	}

	private void updateProjectDB() {

		try {
			Thread updateThread = new Thread(new UpdateProjetoServer());
			updateThread.start();
			updateThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
