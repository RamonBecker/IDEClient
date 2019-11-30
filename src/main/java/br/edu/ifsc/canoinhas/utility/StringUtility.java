package br.edu.ifsc.canoinhas.utility;

public class StringUtility {


	public static String completeOperation = "Operação realizada com sucesso !";
	
	public static String erro = "Erro para realizar esta operação";

	public static String running = "Running";

	public static String textLocalPrompt = "\\local\\local\\local";

	public static String classeCreate = "Classe criado";

	public static String pacoteCreate = "Pacote criado";

	public static String projectCreate = "O projeto foi criado";

	public static String mainClass = "public static void main(String[] args) {\n" + "\n\n\tSystem.out.print"
			+ "('Ola mundo');\n\n" + "\t}\n" + "}";
	public static String olaMundo = "Ola mundo";

	public static String loginSucess = "Login realizado com sucesso";
	public static String loginIncorret = "Usuario e/ou senha invalidos";
	public static String alterUser = "Usuario alterado com sucesso";
	public static String alterPass = "Senha alterada com sucesso";

	public static String deslocarViatura = "Viaturas em deslocamento";

	public static String registerEmpresaSucess = "Empresa cadastrada com sucesso ";
	public static String registerAlterEmpresaSucess = "Dados alterados com sucesso ";
	public static String registerNotFoundEmpresaSucess = "Empresa não encontrada";

	public static String classeAtenderOcorrencia = "public class TelaAtenderOcorrenciaController {\r\n" + "	@FXML\r\n"
			+ "	private TextField txtRua;\r\n" + "\r\n" + "	@FXML\r\n" + "	private TextField txtTelefone;\r\n" + "\r\n"
			+ "	@FXML\r\n" + "	private RadioButton radioLeve;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private ToggleGroup group1;\r\n" + "\r\n" + "	@FXML\r\n" + "	private RadioButton radioMedio;\r\n"
			+ "\r\n" + "	@FXML\r\n" + "	private RadioButton radioAlta;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private TextField txtNomeVitima;\r\n" + "\r\n" + "	@FXML\r\n" + "	private TextField txtBairro;\r\n"
			+ "\r\n" + "	@FXML\r\n" + "	private TextField txtNumero;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private TextField txtComplemento;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private Button btnDeslocarViatura;\r\n" + "\r\n"
			+ "	public void registerOcorrencia(ActionEvent actionEvent) {\r\n" + "\r\n"
			+ "		String textoRadio = null;\r\n" + "\r\n" + "		if (radioAlta.isSelected()) {\r\n"
			+ "			textoRadio = radioAlta.getText();\r\n" + "		}\r\n" + "\r\n"
			+ "		if (radioMedio.isSelected()) {\r\n" + "			textoRadio = radioMedio.getText();\r\n"
			+ "		}\r\n" + "\r\n" + "		if (radioLeve.isSelected()) {\r\n"
			+ "			textoRadio = radioLeve.getText();\r\n" + "		}\r\n" + "\r\n"
			+ "		ControllerOcorrencia controllerOcorrencia = ControllerOcorrencia.getInstance();\r\n" + "\r\n"
			+ "		Endereco endereco = new Endereco(txtRua.getText(), txtBairro.getText(), txtNumero.getText(),\r\n"
			+ "				txtTelefone.getText());\r\n" + "\r\n"
			+ "		Ocorrencia ocorrencia = new Ocorrencia(txtNomeVitima.getText(), textoRadio, endereco);\r\n"
			+ "		controllerOcorrencia.addOcorrencia(ocorrencia);\r\n"
			+ "		MessageAlert.mensagemRealizadoSucesso(StringUtility.deslocarViatura);\r\n"
			+ "		cleanfield();\r\n" + "	}\r\n" + "\r\n" + "	private void cleanfield() {\r\n"
			+ "		radioAlta.setSelected(false);\r\n" + "		radioLeve.setSelected(false);\r\n"
			+ "		radioMedio.setSelected(false);\r\n" + "		txtBairro.setText(\"\");\r\n"
			+ "		txtComplemento.setText(\"\");\r\n" + "		txtNomeVitima.setText(\"\");\r\n"
			+ "		txtNumero.setText(\"\");\r\n" + "		txtRua.setText(\"\");\r\n"
			+ "		txtTelefone.setText(\"\");\r\n" + "	}\r\n" + "	\r\n" + "	public void back() {\r\n"
			+ "		try {\r\n" + "			Stage myWin = (Stage) btnDeslocarViatura.getScene().getWindow();\r\n"
			+ "\r\n" + "			Stage stage = new Stage();\r\n"
			+ "			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaPrincipal.fxml\"));\r\n"
			+ "			Parent root;\r\n" + "			root = (Parent) fxmlLoader.load();\r\n"
			+ "			stage.setScene(new Scene(root));\r\n" + "			stage.show();\r\n"
			+ "			myWin.close();\r\n" + "\r\n" + "		} catch (IOException e) {\r\n"
			+ "			e.printStackTrace();\r\n" + "		}\r\n" + "	}\r\n" + "}\r\n" + "";

	public static String telaPrincipal = "\r\n" + "public class TelaPrincipalController {\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private MenuItem menuItemUserPassword;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private Button btnBuscarEmpresa;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private Button btnAtenderOcorrencia;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private Button btnCadastrarEmpresa;\r\n" + "	\r\n" + "\r\n" + "\r\n"
			+ "	public void resetUserPassword() {\r\n" + "		try {\r\n"
			+ "			Stage myWin = (Stage) btnBuscarEmpresa.getScene().getWindow();\r\n" + "\r\n"
			+ "			Stage stage = new Stage();\r\n"
			+ "			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaRedefinirSenhaUsuario.fxml\"));\r\n"
			+ "			Parent root;\r\n" + "			root = (Parent) fxmlLoader.load();\r\n"
			+ "			TelaRedefinirSenhaUsuarioController.telaAtual = \"Tela principal\";\r\n"
			+ "			stage.setScene(new Scene(root));\r\n" + "			stage.show();\r\n"
			+ "			myWin.close();\r\n" + "\r\n" + "		} catch (IOException e) {\r\n"
			+ "			e.printStackTrace();\r\n" + "		}\r\n" + "	}\r\n" + "\r\n" + "	public void logoff() {\r\n"
			+ "		Stage stage = (Stage) btnBuscarEmpresa.getScene().getWindow();\r\n" + "		stage.close();\r\n"
			+ "	}\r\n" + "	\r\n" + "	\r\n" + "	public void atenderOcorrencia() {\r\n"
			+ "		abrirTelas(\"Atender ocorrencia\");\r\n" + "	}\r\n" + "	\r\n"
			+ "	public void buscarEmpresa() {\r\n" + "		abrirTelas(\"Buscar empresa\"); \r\n" + "	}\r\n"
			+ "	\r\n" + "	public void cadastrarEmpresa() {\r\n" + "		abrirTelas(\"Cadastrar empresa\");\r\n"
			+ "	}\r\n" + "	\r\n" + "	public void abrirTelas(String tela) {\r\n" + "\r\n" + "		try {\r\n"
			+ "			FXMLLoader fxmlLoader = null;\r\n" + "			\r\n"
			+ "			if (tela.equals(\"Buscar empresa\")) {\r\n"
			+ "				fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaBuscarEmpresa.fxml\"));\r\n"
			+ "				\r\n" + "			}  if (tela.equals(\"Atender ocorrencia\")) {\r\n"
			+ "				fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaAtenderOcorrencia.fxml\"));\r\n"
			+ "				\r\n" + "			}  if (tela.equals(\"Cadastrar empresa\")) {\r\n"
			+ "				fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaCadastroEmpresa.fxml\"));\r\n"
			+ "			}\r\n" + "\r\n" + "			Stage myWin = (Stage) btnBuscarEmpresa.getScene().getWindow();\r\n"
			+ "\r\n" + "			Stage stage = new Stage();\r\n" + "			Parent root;\r\n" + "\r\n"
			+ "			root = (Parent) fxmlLoader.load();\r\n" + "\r\n"
			+ "			stage.setScene(new Scene(root));\r\n" + "			stage.show();\r\n"
			+ "			myWin.close();\r\n" + "\r\n" + "		} catch (IOException e) {\r\n"
			+ "			e.printStackTrace();\r\n" + "		}\r\n" + "	}";

	public static String buscarEmpresa = "public class TelaBuscarEmpresaController {\r\n" + "	\r\n" + "	  @FXML\r\n"
			+ "	    private TableView<Empresa> tableEmpresa;\r\n" + "\r\n" + "	    @FXML\r\n"
			+ "	    private TableColumn<Empresa, String> columnNome;\r\n" + "\r\n" + "	    @FXML\r\n"
			+ "	    private TableColumn<Empresa, String> columnCNPJ;\r\n" + "\r\n" + "	    @FXML\r\n"
			+ "	    private TableColumn<Empresa, Endereco> columnEndereco;\r\n" + "\r\n" + "	    @FXML\r\n"
			+ "	    private MenuItem menuItemBack;\r\n" + "\r\n" + "	    @FXML\r\n"
			+ "	    private TextField txtNameEmpresa;\r\n" + "\r\n" + "	    @FXML\r\n"
			+ "	    private TextField txtRua;\r\n" + "\r\n" + "	    @FXML\r\n" + "	    private TextField txtCNPJ;\r\n"
			+ "\r\n" + "	    @FXML\r\n" + "	    private TextField txtCidade;\r\n" + "\r\n" + "	    @FXML\r\n"
			+ "	    private TextField txtBairro;\r\n" + "\r\n" + "	    @FXML\r\n"
			+ "	    private TextField txtCEP;\r\n" + "\r\n" + "	    @FXML\r\n" + "	    private Button btnSalvar;\r\n"
			+ "\r\n" + "	    @FXML\r\n" + "	    private Button btnEditar;\r\n" + "	    \r\n" + "	    @FXML\r\n"
			+ "	    private TextField txtEstado;\r\n" + "	    \r\n" + "	    @FXML\r\n"
			+ "	    private TextField txtNumero;\r\n" + "	    \r\n" + "	    private Empresa empresa;\r\n"
			+ "	\r\n" + "	public void atualizarDados() {\r\n" + "		\r\n"
			+ "		columnCNPJ.setCellValueFactory(new PropertyValueFactory<Empresa, String>(\"cnpj\"));\r\n"
			+ "		columnNome.setCellValueFactory(new PropertyValueFactory<Empresa, String>(\"nome\"));\r\n"
			+ "		columnEndereco.setCellValueFactory(new PropertyValueFactory<Empresa, Endereco>(\"endereco\"));\r\n"
			+ "\r\n" + "		ControllerEmpresa controllerEmpresa = ControllerEmpresa.getInstance();\r\n"
			+ "		\r\n" + "		ObservableList<Empresa> listEmpresa = FXCollections.observableArrayList();\r\n"
			+ "		\r\n" + "		\r\n" + "		listEmpresa.addAll(controllerEmpresa.getListEmpresa());\r\n"
			+ "		\r\n" + "		tableEmpresa.setItems(listEmpresa);		\r\n" + "		\r\n" + "	}\r\n"
			+ "	\r\n" + "	public void back() {\r\n" + "		try {\r\n"
			+ "			Stage myWin = (Stage) btnSalvar.getScene().getWindow();\r\n" + "\r\n"
			+ "			Stage stage = new Stage();\r\n"
			+ "			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaPrincipal.fxml\"));\r\n"
			+ "			Parent root;\r\n" + "			root = (Parent) fxmlLoader.load();\r\n"
			+ "			stage.setScene(new Scene(root));\r\n" + "			stage.show();\r\n"
			+ "			myWin.close();\r\n" + "\r\n" + "		} catch (IOException e) {\r\n"
			+ "			e.printStackTrace();\r\n" + "		}\r\n" + "	}\r\n" + "	\r\n"
			+ "	public void selectedEmpresa() {\r\n" + "		\r\n"
			+ "		txtNameEmpresa.setText(tableEmpresa.getSelectionModel().getSelectedItem().getNome());\r\n"
			+ "		txtCNPJ.setText(tableEmpresa.getSelectionModel().getSelectedItem().getCnpj());\r\n"
			+ "		txtBairro.setText(tableEmpresa.getSelectionModel().getSelectedItem().getEndereco().getBairro());\r\n"
			+ "		txtCEP.setText(tableEmpresa.getSelectionModel().getSelectedItem().getEndereco().getCep());\r\n"
			+ "		txtCidade.setText(tableEmpresa.getSelectionModel().getSelectedItem().getEndereco().getCidade());\r\n"
			+ "	    txtNumero.setText(tableEmpresa.getSelectionModel().getSelectedItem().getEndereco().getNumero());\r\n"
			+ "	    txtEstado.setText(tableEmpresa.getSelectionModel().getSelectedItem().getEndereco().getEstado());\r\n"
			+ "	    txtRua.setText(tableEmpresa.getSelectionModel().getSelectedItem().getEndereco().getRua());\r\n"
			+ "	    \r\n" + "		ControllerEmpresa controllerEmpresa = ControllerEmpresa.getInstance();\r\n"
			+ "		\r\n" + "		this.empresa = controllerEmpresa.searchEmpresa(txtCNPJ.getText());\r\n" + "	}\r\n"
			+ "	\r\n" + "	\r\n" + "	public void actionEditar() {\r\n" + "		editar(false);\r\n" + "	}\r\n"
			+ "	\r\n" + "	public void editar(Boolean bool) {\r\n" + "		txtBairro.setDisable(bool);\r\n"
			+ "		txtCEP.setDisable(bool);\r\n" + "		txtCidade.setDisable(bool);\r\n"
			+ "		txtCNPJ.setDisable(bool);\r\n" + "		txtEstado.setDisable(bool);\r\n"
			+ "		txtNameEmpresa.setDisable(bool);\r\n" + "		txtNumero.setDisable(bool);\r\n"
			+ "		txtRua.setDisable(bool);\r\n" + "	}\r\n" + "	\r\n" + "	private void cleanFields() {\r\n"
			+ "		txtBairro.setText(\"\");\r\n" + "		txtCEP.setText(\"\");\r\n"
			+ "		txtCidade.setText(\"\");\r\n" + "		txtCNPJ.setText(\"\");\r\n"
			+ "		txtEstado.setText(\"\");\r\n" + "		txtNameEmpresa.setText(\"\");\r\n"
			+ "		txtNumero.setText(\"\");\r\n" + "		txtRua.setText(\"\");\r\n"
			+ "		txtNameEmpresa.setText(\"\");\r\n" + "		txtCNPJ.setText(\"\");\r\n" + "	}\r\n"
			+ "	public void salvar() {\r\n" + "		\r\n" + "		if(empresa == null) {\r\n"
			+ "			MessageAlert.mensagemErro(StringUtility.registerNotFoundEmpresaSucess);\r\n"
			+ "			return;\r\n" + "		}\r\n" + "		\r\n" + "		empresa.setCnpj(txtCNPJ.getText());\r\n"
			+ "		empresa.setNome(txtNameEmpresa.getText());\r\n"
			+ "		empresa.getEndereco().setBairro(txtBairro.getText());\r\n"
			+ "		empresa.getEndereco().setCep(txtCEP.getText());\r\n"
			+ "		empresa.getEndereco().setCidade(txtCidade.getText());\r\n"
			+ "		empresa.getEndereco().setEstado(txtEstado.getText());\r\n"
			+ "		empresa.getEndereco().setNumero(txtNumero.getText());\r\n"
			+ "		empresa.getEndereco().setRua(txtRua.getText());\r\n" + "		\r\n"
			+ "		MessageAlert.mensagemRealizadoSucesso(StringUtility.registerEmpresaSucess);\r\n" + "		\r\n"
			+ "		cleanFields();\r\n" + "		editar(true);\r\n" + "	}";

	public static String login = "public class TelaLoginController {\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private TextField txtuserName;\r\n" + "\r\n" + "	@FXML\r\n" + "	private Label lblEsqueceuSenha;\r\n"
			+ "\r\n" + "	@FXML\r\n" + "	private Button btnLogin;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private PasswordField txtSenha;\r\n" + "\r\n" + "	public void login(ActionEvent actionEvent) {\r\n"
			+ "		try {\r\n" + "\r\n" + "			ControllerUser controllerUser = ControllerUser.getInstance();\r\n"
			+ "			boolean validarLogin = false;\r\n" + "\r\n"
			+ "			Usuario usuario = controllerUser.searchUser(txtuserName.getText());\r\n" + "			\r\n"
			+ "			\r\n" + "			if(usuario == null) {\r\n"
			+ "				MessageAlert.mensagemErro(StringUtility.loginIncorret);\r\n" + "				return;\r\n"
			+ "			}\r\n" + "			\r\n"
			+ "			if (usuario.getName().equals(txtuserName.getText().trim())\r\n"
			+ "					&& usuario.getPassword().equals(txtSenha.getText().trim())) {\r\n"
			+ "				validarLogin = true;\r\n" + "			}else {\r\n"
			+ "				MessageAlert.mensagemErro(StringUtility.loginIncorret);\r\n" + "				return;\r\n"
			+ "			}\r\n" + "\r\n" + "			if (validarLogin) {\r\n" + "\r\n"
			+ "				Stage myWin = (Stage) btnLogin.getScene().getWindow();\r\n" + "\r\n"
			+ "				Stage stage = new Stage();\r\n"
			+ "				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaPrincipal.fxml\"));\r\n"
			+ "				Parent root;\r\n" + "\r\n" + "				root = (Parent) fxmlLoader.load();\r\n"
			+ "				stage.setScene(new Scene(root));\r\n" + "				stage.show();\r\n"
			+ "				myWin.close();\r\n" + "			}\r\n" + "\r\n" + "		} catch (IOException e1) {\r\n"
			+ "			e1.printStackTrace();\r\n" + "		}\r\n" + "	}\r\n" + "\r\n"
			+ "	public void esqueceuSenha() {\r\n" + "\r\n" + "		try {\r\n" + "\r\n"
			+ "			Stage myWin = (Stage) btnLogin.getScene().getWindow();\r\n" + "\r\n"
			+ "			Stage stage = new Stage();\r\n"
			+ "			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaRedefinirSenhaUsuario.fxml\"));\r\n"
			+ "			Parent root;\r\n" + "			TelaRedefinirSenhaUsuarioController.telaAtual = \"Login\";\r\n"
			+ "			root = (Parent) fxmlLoader.load();\r\n" + "\r\n"
			+ "			stage.setScene(new Scene(root));\r\n" + "			stage.show();\r\n"
			+ "			myWin.close();\r\n" + "		} catch (IOException e) {\r\n"
			+ "			e.printStackTrace();\r\n" + "		}\r\n" + "	}";

	public static String cadastrarEmpresa = "public class TelaCadastrarEmpresaController {\r\n" + "	@FXML\r\n"
			+ "	private MenuItem menuItemBack;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private TextField txtNameEmpresa;\r\n" + "\r\n" + "	@FXML\r\n" + "	private TextField txtCNPJ;\r\n"
			+ "\r\n" + "	@FXML\r\n" + "	private TextField txtRua;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private TextField txtBairro;\r\n" + "\r\n" + "	@FXML\r\n" + "	private TextField txtNumero;\r\n"
			+ "\r\n" + "	@FXML\r\n" + "	private TextField txtCidade;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private TextField txtCEP;\r\n" + "\r\n" + "	@FXML\r\n" + "	private TextField txtEstado;\r\n" + "\r\n"
			+ "	@FXML\r\n" + "	private Button btnCadastrar;\r\n" + "\r\n" + "	private TextArea area;\r\n" + "	\r\n"
			+ "	public TelaCadastrarEmpresaController() {\r\n" + "	}\r\n" + "	\r\n"
			+ "	public void registerEmpresa() {\r\n" + "\r\n"
			+ "		Endereco endereco = new Endereco(txtRua.getText(), txtBairro.getText(), txtNumero.getText(),\r\n"
			+ "				txtEstado.getText(), txtCEP.getText(), txtCidade.getText());\r\n"
			+ "		Empresa empresa = new Empresa(txtNameEmpresa.getText(), txtCNPJ.getText(), endereco);\r\n" + "\r\n"
			+ "		ControllerEmpresa controllerEmpresa = ControllerEmpresa.getInstance();\r\n" + "\r\n"
			+ "		controllerEmpresa.addEmpresa(empresa);\r\n" + "\r\n" + "		\r\n"
			+ "		MessageAlert.mensagemRealizadoSucesso(StringUtility.registerEmpresaSucess);\r\n"
			+ "		cleanFields();\r\n" + "\r\n" + "	}\r\n" + "\r\n" + "	public void back() {\r\n"
			+ "		try {\r\n" + "			Stage myWin = (Stage) btnCadastrar.getScene().getWindow();\r\n" + "\r\n"
			+ "			Stage stage = new Stage();\r\n"
			+ "			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaPrincipal.fxml\"));\r\n"
			+ "			Parent root;\r\n" + "			root = (Parent) fxmlLoader.load();\r\n"
			+ "			stage.setScene(new Scene(root));\r\n" + "			stage.show();\r\n"
			+ "			myWin.close();\r\n" + "\r\n" + "		} catch (IOException e) {\r\n"
			+ "			e.printStackTrace();\r\n" + "		}\r\n" + "	}\r\n" + "\r\n"
			+ "	private void cleanFields() {\r\n" + "		txtBairro.setText(\"\");\r\n"
			+ "		txtCEP.setText(\"\");\r\n" + "		txtCidade.setText(\"\");\r\n"
			+ "		txtCNPJ.setText(\"\");\r\n" + "		txtEstado.setText(\"\");\r\n"
			+ "		txtNameEmpresa.setText(\"\");\r\n" + "		txtNumero.setText(\"\");\r\n"
			+ "		txtRua.setText(\"\");\r\n" + "	}";

	public static String redefinirSenhaUsuario = "public class TelaRedefinirSenhaUsuarioController {\r\n" + "\r\n"
			+ "	public static String telaAtual;\r\n" + "\r\n" + "	@FXML\r\n" + "	private Button btnConfirmar;\r\n"
			+ "\r\n" + "	@FXML\r\n" + "	private RadioButton radioSenha;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private ToggleGroup group1;\r\n" + "\r\n" + "	@FXML\r\n" + "	private RadioButton radioUsuario;\r\n"
			+ "\r\n" + "	@FXML\r\n" + "	private TextField txtUser;\r\n" + "\r\n" + "	@FXML\r\n"
			+ "	private TextField txtNewUser;\r\n" + "\r\n" + "	@FXML\r\n" + "	private PasswordField txtPassword;\r\n"
			+ "\r\n" + "	@FXML\r\n" + "	private PasswordField txtNewPassword;\r\n" + "\r\n"
			+ "	public void alterUserPassword(ActionEvent actionEvent) {\r\n" + "\r\n"
			+ "		if (radioUsuario.isSelected()) {\r\n" + "			txtNewUser.setDisable(false);\r\n"
			+ "			txtNewPassword.setDisable(true);\r\n" + "		}\r\n" + "\r\n"
			+ "		if (radioSenha.isSelected()) {\r\n" + "			txtNewUser.setDisable(true);\r\n"
			+ "			txtNewPassword.setDisable(false);\r\n" + "		}\r\n" + "	}\r\n" + "\r\n"
			+ "	public void registerNewUserPassword() {\r\n"
			+ "		ControllerUser controllerUser = ControllerUser.getInstance();\r\n"
			+ "		Usuario usuario = controllerUser.searchUser(txtUser.getText().trim());\r\n" + "\r\n"
			+ "		if (usuario == null) {\r\n" + "			MessageAlert.mensagemErro(StringUtility.loginIncorret);\r\n"
			+ "			return;\r\n" + "		}\r\n" + "\r\n" + "		if (radioUsuario.isSelected()) {\r\n" + "\r\n"
			+ "			if (usuario.getName().equals(txtUser.getText()) && usuario.getPassword().equals(txtPassword.getText())) {\r\n"
			+ "				usuario.setName(txtNewUser.getText().trim());\r\n"
			+ "				MessageAlert.mensagemRealizadoSucesso(StringUtility.alterUser);\r\n" + "\r\n"
			+ "			} else {\r\n" + "				MessageAlert.mensagemErro(StringUtility.loginIncorret);\r\n"
			+ "				return;\r\n" + "			}\r\n" + "		}\r\n"
			+ "		if (radioSenha.isSelected()) {\r\n" + "\r\n"
			+ "			if (usuario.getName().equals(txtUser.getText()) && usuario.getPassword().equals(txtPassword.getText())) {\r\n"
			+ "				usuario.setName(txtNewUser.getText().trim());\r\n"
			+ "				MessageAlert.mensagemRealizadoSucesso(StringUtility.alterPass);\r\n"
			+ "			} else {\r\n" + "				MessageAlert.mensagemErro(StringUtility.loginIncorret);\r\n"
			+ "				return;\r\n" + "			}\r\n" + "			\r\n"
			+ "			usuario.setPassword(txtNewPassword.getText().trim());\r\n" + "		}\r\n" + "	}\r\n" + "\r\n"
			+ "	public void back() {\r\n" + "		FXMLLoader fxmlLoader = null;\r\n" + "\r\n"
			+ "		System.out.println(telaAtual);\r\n" + "		try {\r\n"
			+ "			if (telaAtual.equals(\"Login\")) {\r\n"
			+ "				fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaLogin.fxml\"));\r\n"
			+ "			}\r\n" + "\r\n" + "			if (telaAtual.equals(\"Tela principal\")) {\r\n"
			+ "				fxmlLoader = new FXMLLoader(App.class.getResource(\"TelaPrincipal.fxml\"));\r\n"
			+ "			}\r\n" + "\r\n" + "			Stage myWin = (Stage) btnConfirmar.getScene().getWindow();\r\n"
			+ "\r\n" + "			Stage stage = new Stage();\r\n" + "\r\n" + "			Parent root;\r\n"
			+ "			root = (Parent) fxmlLoader.load();\r\n" + "\r\n"
			+ "			stage.setScene(new Scene(root));\r\n" + "			stage.show();\r\n"
			+ "			myWin.close();\r\n" + "\r\n" + "		} catch (IOException e) {\r\n"
			+ "			e.printStackTrace();\r\n" + "		}\r\n" + "\r\n" + "	}";

	public static String main = "public class Main{" + "\n" + "public static void main(String [] args){" + "\n"
			+ "\t\tlauch();\n" + "\t}" + "}";

	public static String statusVazio = "O status da ocorrencia não pode ser vazio !";
	public static String ruaVazio = "A rua não pode ser vazio !";
	public static String bairroVazio = "O bairro não pode ser vazio !";
	public static String cidadeVazio = "A cidade não pode ser vazio !";
	public static String estadoVazio = "O Estado não pode ser vazio !";
	public static String cepVazio = "O CEP não pode ser vazio !";
	public static String telefoneVazio = "O telefone não pode ser vazio !";
	public static String numeroVazio = "O numero não pode ser vazio !";
	public static String nomeVazio = "O nome da empresa não pode ser vazio !";
	public static String cnpjVazio = "O CNPJ não pode ser vazio !";
	public static String nomePacoteVazio = "O nome do pacote não pode ser vazio !";
	public static String localDefaultVazio = "O local não pode ser vazio !";
	public static String nomeProjetoVazio = "O nome do projeto não pode ser vazio !";
	public static String nomeClasseVazio = "O nome da classe não pode ser vazio !";
	public static String codigoVazioClasse = "O código da classe não pode ser vazio !";
	public static String nomeVitimaVazio = "O nome da vitima não pode ser vazio !";
	public static String gravidadeVazio = "A gravidade da ocorrência não pode ser vazio !";
	public static String enderecoVazio = "O endereço não pode ser vazio !";
	public static String nomeUsuarioVazio = "O nome do usuário não pode ser vazio !";
	public static String senhaVazio = "A senha não pode ser vazia !";
	public static String projetoIguais = "Este projeto não pode ser criado, pois já existe um com este nome!";
	public static String noSelectedLocation = "Selecione se deseja manter o default local, ou alterá-lo !";
	public static String packageExisting = "Não foi possível criar este pacote, porque ele já existe !";
	public static String projectNull = "O pacote não pode ser criado, porque não existe referência de projeto !";
	public static String selectedUserPassword = "Selecione as opções de: usuário, senha e cadastrar !";
	public static String selectedNull = "Selecione alguma opção: Delete ou Edit !";
	public static String selectedNullEdit = "Selecione alguma opção: Projeto, Pacote ou Classe !";
	public static String createUser = "Usuário cadastrado com sucesso !";
	public static String complementoVazio = "O complemento não pode ser vazio !";
	public static String empresaSelected = "Selecione a empresa !";
	public static String selectedProjeto = "Selecione algum projeto !";
	public static String selectedPacote = "Selecione algum pacote !";
	public static String selectedClasse = "Selecione alguma classe !";
	public static String nameProjectSucessEdit = "Nome do projeto editado com sucesso !";
	public static String namePackageSucessEdit = "Nome do pacote editado com sucesso !";
	public static String nameClassSucessEdit = "Nome da classe editado com sucesso !";
	public static String removeProject = "Projeto removido com sucesso !";
	public static String removePacote = "Pacote removido com sucesso !";
	public static String removeClass = "Classe removida com sucesso !";
	public static String removeEmpresa = "Empresa removida com sucesso !";
	public static String selectedNullGravidadeOcorrencia = "Selecione a gravidade da ocorrencia !";
	public static String ocorrenciaAndamento = "Ocorrencia em andamento";
	public static String ocorrenciaConcluido = "Ocorrencia concluida";
	public static String viaturaDeslocada = "Viatura em deslocamento para a ocorrência !";
}
