package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.entities.Endereco;
import br.edu.ifsc.canoinhas.entities.Ocorrencia;
import br.edu.ifsc.canoinhas.modelDao.controller.ocorrencia.DaoDBOcorrencia;
import br.edu.ifsc.canoinhas.modelDao.controller.threads.UpdateOcorrenciaServer;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerShowOcorrenciaView implements Initializable {

	@FXML
	private TableView<Ocorrencia> tableViewOcorrencia;

	@FXML
	private TableColumn<Ocorrencia, String> tableColumnNomeVitima;

	@FXML
	private TableColumn<Ocorrencia, Endereco> tableColumnEndereco;

	@FXML
	private TableColumn<Ocorrencia, String> tableColumnGravidade;

	@FXML
	private TableColumn<Ocorrencia, String> tableColumnStatus;

	@FXML
	private TextField txtStatusOcorrencia;

	@FXML
	private Button btnConcluirOcorrencia;

	@FXML
	private MenuItem menuItemBack;

	private DaoDBOcorrencia daoDBOcorrencia;

	private Ocorrencia ocorrencia;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// preencherTabela();
//		try {
//			daoDBOcorrencia.getAllOcorrencia();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		update();
		preencherTabela();
	}

	private void update() {

		try {
			Thread updateOcorrencia = new Thread(new UpdateOcorrenciaServer());
			updateOcorrencia.start();
			updateOcorrencia.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void preencherTabela() {

		tableViewOcorrencia.getItems().clear();

		daoDBOcorrencia = DaoDBOcorrencia.getInstance();

		ObservableList<Ocorrencia> listOcorrencia = FXCollections.observableArrayList();

		tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<Ocorrencia, Endereco>("endereco"));
		tableColumnGravidade.setCellValueFactory(new PropertyValueFactory<Ocorrencia, String>("gravidade"));
		tableColumnNomeVitima.setCellValueFactory(new PropertyValueFactory<Ocorrencia, String>("nomeVitima"));
		tableColumnStatus.setCellValueFactory(new PropertyValueFactory<Ocorrencia, String>("status"));

		for (Ocorrencia ocorrencia : daoDBOcorrencia.getListOcorrencias()) {
			System.out.println(ocorrencia);
			if (ocorrencia.getStatus().equals(StringUtility.ocorrenciaAndamento)) {
				listOcorrencia.add(ocorrencia);
			}
		}

		tableViewOcorrencia.setItems(listOcorrencia);

	}

	public void actionTableOcorrencia() {
		ocorrencia = tableViewOcorrencia.getSelectionModel().getSelectedItem();

		if (ocorrencia != null) {
			txtStatusOcorrencia.setText(ocorrencia.getStatus());
		}
	}

	public void ocorrenciaComplete() {
//		
		daoDBOcorrencia = DaoDBOcorrencia.getInstance();

		ocorrencia.setStatus(txtStatusOcorrencia.getText().trim());

		if (ocorrencia != null) {
			daoDBOcorrencia.editOcorrencia(ocorrencia);
			txtStatusOcorrencia.setText("");
			MessageAlert.mensagemRealizadoSucesso(StringUtility.ocorrenciaConcluido);
		}

		preencherTabela();
	}

	public void back() {
		try {
			Stage myWin = (Stage) btnConcluirOcorrencia.getScene().getWindow();

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
}
