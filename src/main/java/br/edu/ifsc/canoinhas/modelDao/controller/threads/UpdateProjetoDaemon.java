package br.edu.ifsc.canoinhas.modelDao.controller.threads;

import java.io.IOException;

import br.edu.ifsc.canoinhas.modelDao.controller.projeto.DaoDBProjeto;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import javafx.application.Platform;

public class UpdateProjetoDaemon implements Runnable {

	@Override
	public void run() {
		try {
			DaoDBProjeto controllerDBProjeto = DaoDBProjeto.getInstance();
			controllerDBProjeto.getListProjeto().clear();
			controllerDBProjeto.getAllProjeto();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
