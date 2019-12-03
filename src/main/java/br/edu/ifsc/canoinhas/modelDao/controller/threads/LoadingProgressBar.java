package br.edu.ifsc.canoinhas.modelDao.controller.threads;

import java.io.IOException;

import br.edu.ifsc.canoinhas.controllerview.ControllerLoadingView;
import br.edu.ifsc.canoinhas.utility.ControllerReferenceIDE;
import br.edu.ifsc.canoinhas.utility.StringUtility;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class LoadingProgressBar implements Runnable {

	private ControllerLoadingView controllerLoadingView;
	private ProgressBar progressBar;
	private Thread updateProjeto;
	// private String view = "";

	public LoadingProgressBar(ControllerLoadingView controllerLoadingView) {
		this.controllerLoadingView = controllerLoadingView;
	}

//	public void setView(String view) {
//		this.view = view;
//	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				double value = controllerLoadingView.getProgressBar().getProgress();
				value += 0.20;
				controllerLoadingView.getProgressBar().setProgress(value);
				Thread.sleep(2000);

				if (controllerLoadingView.getProgressBar().getProgress() == 0.4) {

					if (ControllerLoadingView.view.equals("IDEView")) {
						controllerLoadingView.getLabelBuscarDados().setVisible(false);
						controllerLoadingView.getLabelInserindoDados().setVisible(true);

					}

					if (ControllerLoadingView.view.equals("Run")) {
						controllerLoadingView.getLabelBuscarDados().setVisible(false);
						controllerLoadingView.getLbCriandoProjeto().setVisible(true);
					}
				}

				if (controllerLoadingView.getProgressBar().getProgress() == 0.8) {
					if (ControllerLoadingView.view.equals("IDEView")) {
						controllerLoadingView.getLabelInserindoDados().setVisible(false);
						controllerLoadingView.getLabelQuasela().setVisible(true);
					}
					if (ControllerLoadingView.view.equals("Run")) {
						controllerLoadingView.getLbCriandoProjeto().setVisible(false);
						controllerLoadingView.getLblExecutandoPrograma().setVisible(true);
					}
				}

				if (controllerLoadingView.getProgressBar().getProgress() == 1.0) {

					if (ControllerLoadingView.view.equals("IDEView")) {
						controllerLoadingView.getLabelQuasela().setVisible(false);

						controllerLoadingView.getLabelIniciandoProjeto().setVisible(true);
						Thread.sleep(2000);
					}

					Platform.runLater(() -> {
						try {

							if (ControllerLoadingView.view.equals("IDEView")) {
								Thread.sleep(2000);
								controllerLoadingView.closeWindow();
							}

							if (ControllerLoadingView.view.equals("Run")) {
								Thread.sleep(2000);
								Scene scene = controllerLoadingView.getBtnCancelar().getScene();
								Stage stage = (Stage) scene.getWindow();
								stage.close();
								
								ControllerReferenceIDE controllerReferenceIDE = ControllerReferenceIDE.getInstace();
								
								if(controllerReferenceIDE.getControllerIDEView().getTextAreaProgram().getText().contains("Ola mundo")){
									controllerReferenceIDE.getControllerIDEView().getTextAreaConsole().setText(StringUtility.olaMundo);
								}
								
								//		if (textAreaProgram.getText().contains("Ola mundo")) {
//								textAreaConsole.setText(StringUtility.olaMundo);
//								}
								
				
								
							}
							Thread.currentThread().interrupt();
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
					});

					Thread.currentThread().interrupt();

				}
			}

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			// e.printStackTrace();
		}

	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public Thread getUpdateProjeto() {
		return updateProjeto;
	}

//	public String getView() {
//		return view;
//	}

}
