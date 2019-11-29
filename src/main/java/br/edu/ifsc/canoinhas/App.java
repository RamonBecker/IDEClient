package br.edu.ifsc.canoinhas;

import java.io.IOException;

import br.edu.ifsc.canoinhas.modelDao.Conn;
import br.edu.ifsc.canoinhas.modelDao.controller.projeto.UpdateProjetoDaemon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException, InterruptedException {

		Thread requestProjeto = new Thread(new UpdateProjetoDaemon());

		requestProjeto.start();
		requestProjeto.join();

		scene = new Scene(loadFXML("TelaIDEPrincipal"));
		stage.setScene(scene);
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

}