package br.edu.ifsc.canoinhas.controllerview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import br.edu.ifsc.canoinhas.App;
import br.edu.ifsc.canoinhas.modelDao.controller.threads.LoadingProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class ControllerLoadingView implements Initializable {

	@FXML
	private Button btnCancelar;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private Label labelBuscarDados;

	@FXML
	private Label labelInserindoDados;

	@FXML
	private Label labelQuasela;

	@FXML
	private Label labelIniciandoProjeto;

	@FXML
	private Label labelInterrompeu;

	@FXML
	private Button btnIniciarNovamente;

	@FXML
	private Label lbCriandoProjeto;

	@FXML
	private Label lblExecutandoPrograma;

	private Thread loading;

	private LoadingProgressBar progress;

	public static String view = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progress = new LoadingProgressBar(this);
		loading = new Thread(progress);
		loading.start();
	}

	public void cancelUpdate() {
		if (loading.isAlive()) {
			labelBuscarDados.setVisible(false);
			labelIniciandoProjeto.setVisible(false);
			labelInserindoDados.setVisible(false);
			labelQuasela.setVisible(false);
			labelInterrompeu.setVisible(true);

			lbCriandoProjeto.setVisible(false);
			lblExecutandoPrograma.setVisible(false);

			btnCancelar.setVisible(false);
			btnIniciarNovamente.setVisible(true);
			loading.interrupt();
		}
	}

	public void iniciarNovamente() {
		progressBar.setProgress(0.0);

		if (ControllerLoadingView.view.equals("IDEView")) {
			labelInterrompeu.setVisible(false);
			labelBuscarDados.setVisible(true);
			btnCancelar.setVisible(true);
			btnIniciarNovamente.setVisible(false);
			progress = new LoadingProgressBar(this);

		}

		if (ControllerLoadingView.view.equals("Run")) {
			labelInterrompeu.setVisible(false);
			labelBuscarDados.setVisible(true);
			btnCancelar.setVisible(true);
			btnIniciarNovamente.setVisible(false);
			progress = new LoadingProgressBar(this);
		}
		loading = new Thread(progress);
		loading.start();
	}

	public void closeWindow() throws IOException {
		Scene scene = progressBar.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
		FXMLLoader fxmlLoader = null;
		if (ControllerLoadingView.view.equals("IDEView")) {
			fxmlLoader = new FXMLLoader(App.class.getResource("TelaIDEPrincipal.fxml"));
		}
		Parent parent = fxmlLoader.load();
		Scene newScene = new Scene(parent);
		Stage newStage = new Stage();
		newStage.setScene(newScene);
		newStage.show();
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public Label getLabelBuscarDados() {
		return labelBuscarDados;
	}

	public Label getLabelInserindoDados() {
		return labelInserindoDados;
	}

	public Label getLabelQuasela() {
		return labelQuasela;
	}

	public Label getLabelIniciandoProjeto() {
		return labelIniciandoProjeto;
	}

	public Label getLblExecutandoPrograma() {
		return lblExecutandoPrograma;
	}

	public Label getLbCriandoProjeto() {
		return lbCriandoProjeto;
	}

	public Thread getLoading() {
		return loading;
	}

}
