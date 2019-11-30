package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifsc.canoinhas.entities.Classe;
import br.edu.ifsc.canoinhas.entities.Pacote;
import br.edu.ifsc.canoinhas.entities.Projeto;
import br.edu.ifsc.canoinhas.modelDao.controller.projeto.DaoDBProjeto;
import br.edu.ifsc.canoinhas.modelDao.controller.projeto.UpdateProjetoDaemon;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerEditProject implements Initializable {

	@FXML
	private RadioButton radioButtonDelete;

	@FXML
	private RadioButton radioButtonEdit;

	@FXML
	private Button btnSave;

	@FXML
	private TextField txtName;

	@FXML
	private RadioButton radioButtonProject;

	@FXML
	private ToggleGroup group;

	@FXML
	private RadioButton radioButtonPackage;

	@FXML
	private RadioButton radioButtonClass;

	@FXML
	private TableView<Projeto> tableViewProjeto;

	@FXML
	private TableColumn<Projeto, String> tableColumnProjeto;

	@FXML
	private TableView<Pacote> tableViewPacote;

	@FXML
	private TableColumn<Pacote, String> tableColumnPacote;

	@FXML
	private TableView<Classe> tableViewClass;

	@FXML
	private TableColumn<Classe, String> tableColumnClasse;

	@FXML
	private Button btnBackProject;

	@FXML
	private Button btnPackage;

	@FXML
	private MenuItem menuItemClose;

	@FXML
	private Button btnDelete;

	@FXML
	private ToggleGroup groupOption;

	private Projeto projeto;

	private Pacote pacote;

	private Classe classe;

	private DaoDBProjeto daoDBProjeto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		preecherTabelaProjeto();
	}

	public void saveEdit() {

		daoDBProjeto = DaoDBProjeto.getInstance();

		if (groupOption.getSelectedToggle() == null) {
			MessageAlert.mensagemErro(StringUtility.selectedNull);
			return;
		}

		if (group.getSelectedToggle() == null) {
			MessageAlert.mensagemErro(StringUtility.selectedNullEdit);
			return;
		}

		if (radioButtonProject.isSelected()) {
			if (projeto != null) {
				try {
					daoDBProjeto.submitIdProjectServer(String.valueOf(projeto.getId()), txtName.getText().trim(),
							"edit");

					Thread update = new Thread(new UpdateProjetoDaemon());
					update.start();
					update.join();
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				MessageAlert.mensagemErro(StringUtility.selectedProjeto);
				return;
			}
		}

		if (radioButtonPackage.isSelected()) {
			if (pacote != null) {

				pacote.setNome(txtName.getText().trim());
				daoDBProjeto.editPacote(pacote);
				tableViewPacote.setVisible(false);
				tableViewProjeto.setVisible(true);
				MessageAlert.mensagemRealizadoSucesso(StringUtility.namePackageSucessEdit);

			} else {
				MessageAlert.mensagemErro(StringUtility.selectedPacote);
				return;
			}

		}

		if (radioButtonClass.isSelected()) {
			if (classe != null) {

				classe.setNome(txtName.getText());

				classe.setCodigoClasse(true, classe.getTypeClasse());
				// pacote.setCodigoClasse(true, "public", classe);

				daoDBProjeto.editClass(classe);

				tableViewClass.setVisible(false);
				tableViewPacote.setVisible(false);
				tableViewProjeto.setVisible(true);

				MessageAlert.mensagemRealizadoSucesso(StringUtility.nameClassSucessEdit);
			} else {
				MessageAlert.mensagemErro(StringUtility.selectedClasse);
				return;
			}
		}

		clean();
		preecherTabelaProjeto();
	}

	public void remove() {
		daoDBProjeto = DaoDBProjeto.getInstance();

		if (groupOption.getSelectedToggle() == null) {
			MessageAlert.mensagemErro(StringUtility.selectedNull);
			return;
		}

		if (group.getSelectedToggle() == null) {
			MessageAlert.mensagemErro(StringUtility.selectedNullEdit);
			return;
		}

		if (radioButtonProject.isSelected()) {
			if (projeto != null) {
				daoDBProjeto.removeProject(projeto);
				MessageAlert.mensagemRealizadoSucesso(StringUtility.removeProject);
			} else {
				MessageAlert.mensagemErro(StringUtility.selectedProjeto);
				return;
			}
		}

		if (radioButtonPackage.isSelected()) {
			if (pacote != null) {
				daoDBProjeto.removePacote(pacote);
				tableViewPacote.setVisible(false);
				tableViewProjeto.setVisible(true);
				MessageAlert.mensagemRealizadoSucesso(StringUtility.removePacote);
			} else {
				MessageAlert.mensagemErro(StringUtility.selectedPacote);
				return;
			}
		}

		if (radioButtonClass.isSelected()) {
			if (classe != null) {
				tableViewClass.setVisible(false);
				tableViewProjeto.setVisible(true);
				daoDBProjeto.removeClass(classe);
				MessageAlert.mensagemRealizadoSucesso(StringUtility.removeClass);
			} else {
				MessageAlert.mensagemErro(StringUtility.selectedClasse);
				return;
			}
		}

		clean();
		preecherTabelaProjeto();
	}

	private void preecherTabelaProjeto() {

		tableViewProjeto.getItems().clear();

		ObservableList<Projeto> listProjeto = FXCollections.observableArrayList();

		tableColumnProjeto.setCellValueFactory(new PropertyValueFactory<Projeto, String>("nome"));

		daoDBProjeto = DaoDBProjeto.getInstance();

		listProjeto.addAll(daoDBProjeto.getListProjeto());

		tableViewProjeto.setItems(listProjeto);
	}

	public void actionTableProjeto() {

		if (groupOption.getSelectedToggle() == null) {
			MessageAlert.mensagemErro(StringUtility.selectedNull);
			return;
		}

		if (group.getSelectedToggle() == null) {
			MessageAlert.mensagemErro(StringUtility.selectedNullEdit);
			return;
		}

		projeto = tableViewProjeto.getSelectionModel().getSelectedItem();

		if (radioButtonProject.isSelected()) {
			txtName.setText(projeto.getNome());
			return;
		}

		btnBackProject.setDisable(false);

		tableViewProjeto.setVisible(false);

		tableViewPacote.setVisible(true);

		tableViewPacote.getItems().clear();

		ObservableList<Pacote> listPacote = FXCollections.observableArrayList();

		listPacote.addAll(projeto.getListPacote());

		tableColumnPacote.setCellValueFactory(new PropertyValueFactory<Pacote, String>("nome"));

		tableViewPacote.setItems(listPacote);

	}

	public void actionTablePacote() {

		if (groupOption.getSelectedToggle() == null) {
			MessageAlert.mensagemErro(StringUtility.selectedNull);
			return;
		}

		if (group.getSelectedToggle() == null) {
			MessageAlert.mensagemErro(StringUtility.selectedNullEdit);
			return;
		}

		// btnBackProject.setDisable(false);

		pacote = tableViewPacote.getSelectionModel().getSelectedItem();

		if (radioButtonPackage.isSelected()) {
			txtName.setText(pacote.getNome());
			return;
		}

		tableViewPacote.setVisible(false);
		tableViewClass.setVisible(true);

		preencherTabelaClasse(pacote);

		btnPackage.setDisable(false);
		btnBackProject.setDisable(false);
	}

	private void preencherTabelaClasse(Pacote pacote) {

		tableViewClass.getItems().clear();

		ObservableList<Classe> listClasse = FXCollections.observableArrayList();

		tableColumnClasse.setCellValueFactory(new PropertyValueFactory<Classe, String>("nome"));

		listClasse.addAll(pacote.getListClasse());

		tableViewClass.setItems(listClasse);
	}

	public void actionTableClass() {

		classe = tableViewClass.getSelectionModel().getSelectedItem();

		if (radioButtonClass.isSelected()) {
			txtName.setText(classe.getNome());
		}
	}

	public void actionEditDelete() {
		if (radioButtonDelete.isSelected()) {
			btnSave.setDisable(true);
			btnDelete.setDisable(false);
			txtName.setDisable(true);
			txtName.setText("");
		}
		if (radioButtonEdit.isSelected()) {
			btnDelete.setDisable(true);
			btnSave.setDisable(false);
			txtName.setDisable(false);
			txtName.setText("");
		}
	}

	public void backProjeto() {

		btnBackProject.setDisable(true);
		btnPackage.setDisable(true);
		tableViewClass.setVisible(false);
		tableViewPacote.setVisible(false);
		tableViewProjeto.setVisible(true);
		txtName.setText("");
	}

	public void backPacote() {

		tableViewClass.setVisible(false);
		tableViewProjeto.setVisible(false);
		tableViewPacote.setVisible(true);
		txtName.setText("");

	}

	private void clean() {
		txtName.setText("");
		group.getSelectedToggle().setSelected(false);
		groupOption.getSelectedToggle().setSelected(false);

	}

	public void close() {

		Stage myStage = (Stage) btnSave.getScene().getWindow();
		myStage.close();
	}
}
