package br.edu.ifsc.canoinhas.controllerview;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifsc.canoinhas.entities.Pacote;
import br.edu.ifsc.canoinhas.entities.Projeto;
import br.edu.ifsc.canoinhas.modelDao.controller.projeto.DaoDBClasse;
import br.edu.ifsc.canoinhas.modelDao.controller.projeto.DaoDBProjeto;
import br.edu.ifsc.canoinhas.modelDao.controller.projeto.UpdateProjetoDaemon;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerClassView implements Initializable {
	@FXML
	private RadioButton radioPrivate;

	@FXML
	private ToggleGroup grupo1;

	@FXML
	private TextField txtNameClass;

	@FXML
	private RadioButton radioPublic;

	@FXML
	private CheckBox checkBoxMain;

	@FXML
	private Button btnFinish;

	@FXML
	private Label lbPackage;

	@FXML
	private TextField txtNameProject;

	@FXML
	private TableView<Projeto> tableProject;

	@FXML
	private TableColumn<Projeto, String> tableColumnProject;

	@FXML
	private TableView<Pacote> tableViewPacote;

	@FXML
	private TableColumn<Pacote, String> tableColumnPacote;

	private DaoDBProjeto controllerDBProjeto = DaoDBProjeto.getInstance();

	@FXML
	private Button btnBackProject;

	private Projeto projeto;

	private Pacote pacote;

	public void createClass() {

		try {

			pacote = tableViewPacote.getSelectionModel().getSelectedItem();
			String typeClass = "";

			if (radioPublic.isSelected()) {
				typeClass = "public";
			}

			if (radioPrivate.isSelected()) {
				typeClass = "private";
			}

			Boolean main = checkBoxMain.selectedProperty().getValue();

			pacote.addClass(txtNameClass.getText(), main, typeClass);

			new DaoDBClasse().submitClasseServer(String.valueOf(pacote.getId()), txtNameClass.getText(), main,
					typeClass, "add");

			clearFields();

			Thread update = new Thread(new UpdateProjetoDaemon());
			update.start();
			update.join();

		} catch (IllegalArgumentException | InterruptedException e) {
			MessageAlert.mensagemErro(e.getMessage());
		}

	}

	public void updateProject() {
		projeto = tableProject.getSelectionModel().getSelectedItem();
		tableProject.setVisible(false);

		tableViewPacote.setVisible(true);

		updatePackage(projeto);
	}

	private void updatePackage(Projeto projeto) {

		ObservableList<Pacote> listPacote = FXCollections.observableArrayList();

		tableColumnPacote.setCellValueFactory(new PropertyValueFactory<Pacote, String>("nome"));
		listPacote.addAll(projeto.getListPacote());

		tableViewPacote.setItems(listPacote);
	}

	private void clearFields() {
		txtNameClass.setText("");
		radioPrivate.setSelected(false);
		radioPublic.setSelected(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<Projeto> listProjeto = FXCollections.observableArrayList();
		listProjeto.addAll(controllerDBProjeto.getListProjeto());

		tableColumnProject.setCellValueFactory(new PropertyValueFactory<Projeto, String>("nome"));

		tableProject.setItems(listProjeto);

	}

	public void backProject() {
		tableViewPacote.setVisible(false);
		tableProject.setVisible(true);
	}
}
