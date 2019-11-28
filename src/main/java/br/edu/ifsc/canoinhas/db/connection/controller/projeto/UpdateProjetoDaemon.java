package br.edu.ifsc.canoinhas.db.connection.controller.projeto;

import java.io.IOException;

import br.edu.ifsc.canoinhas.utility.MessageAlert;

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
