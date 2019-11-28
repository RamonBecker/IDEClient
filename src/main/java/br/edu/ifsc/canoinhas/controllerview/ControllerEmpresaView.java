package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.db.connection.controller.DaoDBEmpresa;
import br.edu.ifsc.canoinhas.entities.Empresa;
import br.edu.ifsc.canoinhas.entities.Endereco;
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

	private DaoDBEmpresa controllerDBEmpresa;

	private Empresa empresa;

	private boolean edit = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherTabela();
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

		try {

//			Endereco endereco = new Endereco(txtRua.getText().trim(), txtBairro.getText().trim(),
//					txtNumero.getText().trim(), txtTelefone.getText().trim(), txtEstado.getText().trim(), txtCEP.getText().trim(),
//					txtCidade.getText().trim());

			Endereco endereco = new Endereco(txtRua.getText().trim(), txtBairro.getText().trim(),
					txtNumero.getText().trim(), txtEstado.getText().trim(), txtCEP.getText().trim(),
					txtCidade.getText().trim(), txtTelefone.getText().trim(), "");

			Empresa empresa = new Empresa(txtNameEmpresa.getText().trim(), txtCNPJ.getText().trim(), endereco);

			controllerDBEmpresa = DaoDBEmpresa.getInstance();

			controllerDBEmpresa.addEmpresa(empresa);

			MessageAlert.mensagemRealizadoSucesso(StringUtility.registerEmpresaSucess);
			cleanFields();
			preencherTabela();

		} catch (IllegalArgumentException e) {
			MessageAlert.mensagemErro(e.getMessage());
		}
	}

	public void preencherTabela() {

		tableEmpresa.getItems().clear();

		if (tableEmpresa.getItems().isEmpty()) {

			ObservableList<Empresa> listEmpresa = FXCollections.observableArrayList();

			columnCNPJ.setCellValueFactory(new PropertyValueFactory<Empresa, String>("cnpj"));
			columnEndereco.setCellValueFactory(new PropertyValueFactory<Empresa, Endereco>("endereco"));
			columnNome.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nome"));

			controllerDBEmpresa = DaoDBEmpresa.getInstance();

			listEmpresa.addAll(controllerDBEmpresa.getListEmpresa());

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
			controllerDBEmpresa.deleteEmpresa(empresa);
			MessageAlert.mensagemRealizadoSucesso(StringUtility.removeEmpresa);
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

			controllerDBEmpresa = DaoDBEmpresa.getInstance();

			controllerDBEmpresa.updateEmpresa(empresa);

			btnCadastrar.setDisable(false);
			btnSalvar.setDisable(true);
			btnDeletar.setDisable(false);

			cleanFields();
			preencherTabela();
			MessageAlert.mensagemRealizadoSucesso(StringUtility.registerAlterEmpresaSucess);
		} catch (IllegalArgumentException e) {

			MessageAlert.mensagemErro(e.getMessage());
		}
	}
}
