package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.entities.Empresa;
import br.edu.ifsc.canoinhas.entities.Endereco;
import br.edu.ifsc.canoinhas.modelDao.controller.empresa.DaoDBEmpresa;
import br.edu.ifsc.canoinhas.modelDao.controller.threads.UpdateEmpresaServer;
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

public class ControllerEmpresaView implements Initializable {

	@FXML
	private TableView<Empresa> tableEmpresa;

	@FXML
	private TableColumn<Empresa, String> columnNome;

	@FXML
	private TableColumn<Empresa, String> columnCNPJ;

	@FXML
	private TableColumn<Empresa, Endereco> columnEndereco;

	@FXML
	private MenuItem menuItemBack;

	@FXML
	private TextField txtNameEmpresa;

	@FXML
	private TextField txtRua;

	@FXML
	private TextField txtCNPJ;

	@FXML
	private TextField txtCidade;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtCEP;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnEditar;

	@FXML
	private TextField txtEstado;

	@FXML
	private TextField txtNumero;

	@FXML
	private Button btnDeletar;

	@FXML
	private Button btnCadastrar;

	@FXML
	private TextField txtTelefone;

	private DaoDBEmpresa daoDBEmpresa;

	private Empresa empresa;

	private boolean edit = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		update();
		preencherTabela();
	}

	private void update() {

		try {
			Thread updateEmpresa = new Thread(new UpdateEmpresaServer());
			updateEmpresa.start();
			updateEmpresa.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void back() {
		try {
			Stage myWin = (Stage) btnSalvar.getScene().getWindow();

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

	public void salvar() {

		daoDBEmpresa = DaoDBEmpresa.getInstance();

		daoDBEmpresa.submitEmpresaServer(txtNameEmpresa.getText(), txtCNPJ.getText(), txtRua.getText(),
				txtBairro.getText(), txtNumero.getText(), txtTelefone.getText(), txtEstado.getText(), txtCEP.getText(),
				txtCidade.getText(), "add");
		update();
		cleanFields();
		preencherTabela();

	}

	public void preencherTabela() {

		tableEmpresa.getItems().clear();

		if (tableEmpresa.getItems().isEmpty()) {

			ObservableList<Empresa> listEmpresa = FXCollections.observableArrayList();

			columnCNPJ.setCellValueFactory(new PropertyValueFactory<Empresa, String>("cnpj"));
			columnEndereco.setCellValueFactory(new PropertyValueFactory<Empresa, Endereco>("endereco"));
			columnNome.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nome"));

			daoDBEmpresa = DaoDBEmpresa.getInstance();

			listEmpresa.addAll(daoDBEmpresa.getListEmpresa());

			tableEmpresa.setItems(listEmpresa);
		}
	}

	private void cleanFields() {
		txtBairro.setText("");
		txtCEP.setText("");
		txtCidade.setText("");
		txtCNPJ.setText("");
		txtEstado.setText("");
		txtNameEmpresa.setText("");
		txtNumero.setText("");
		txtRua.setText("");
		txtTelefone.setText("");
	}

	public void actionTable() {
		empresa = tableEmpresa.getSelectionModel().getSelectedItem();

		edit = true;
	}

	public void deleteEmpresa() {

		if (empresa != null) {
			daoDBEmpresa.submitRemoveEmpresaServer(String.valueOf(empresa.getId()), "remove");
			update();
			preencherTabela();
		}
	}

	public void editEmpresa() {

		if (edit) {

			btnCadastrar.setDisable(true);
			btnSalvar.setDisable(false);
			btnDeletar.setDisable(true);

			txtBairro.setText(empresa.getEndereco().getBairro());
			txtCEP.setText(empresa.getEndereco().getCep());
			txtCidade.setText(empresa.getEndereco().getCidade());
			txtEstado.setText(empresa.getEndereco().getEstado());
			txtRua.setText(empresa.getEndereco().getRua());
			txtNumero.setText(empresa.getEndereco().getNumero());
			txtTelefone.setText(empresa.getEndereco().getTelefone());
			txtNameEmpresa.setText(empresa.getNome());
			txtCNPJ.setText(empresa.getCnpj());
			
			edit = false;

		} else {
			MessageAlert.mensagemErro(StringUtility.empresaSelected);
		}
	}

	public void salveEditEmpresa() {

		try {

			empresa.setNome(txtNameEmpresa.getText());
			empresa.setCnpj(txtCNPJ.getText());
			empresa.getEndereco().setBairro(txtBairro.getText());
			empresa.getEndereco().setCep(txtCEP.getText());
			empresa.getEndereco().setCidade(txtCidade.getText());
			empresa.getEndereco().setEstado(txtEstado.getText());
			empresa.getEndereco().setNumero(txtNumero.getText());
			empresa.getEndereco().setRua(txtRua.getText());
			empresa.getEndereco().setTelefone(txtTelefone.getText());

			daoDBEmpresa = DaoDBEmpresa.getInstance();
			
			daoDBEmpresa.submitEmpresaServer(String.valueOf(empresa.getId()),empresa.getNome(), empresa.getCnpj(), 
					empresa.getEndereco().getRua(), empresa.getEndereco().getBairro(), 
					empresa.getEndereco().getNumero(), empresa.getEndereco().getTelefone(), 
					empresa.getEndereco().getEstado(), empresa.getEndereco().getCep(), 
					empresa.getEndereco().getCidade(), "edit");
			
			
			
			btnCadastrar.setDisable(false);
			btnSalvar.setDisable(true);
			btnDeletar.setDisable(false);
			
			update();
			cleanFields();
			preencherTabela();
			MessageAlert.mensagemRealizadoSucesso(StringUtility.registerAlterEmpresaSucess);
		} catch (IllegalArgumentException e) {

			MessageAlert.mensagemErro(e.getMessage());
		}
	}
}
