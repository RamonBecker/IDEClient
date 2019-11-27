package br.edu.ifsc.canoinhas.db.connection.controller.projeto;

import java.io.IOException;

import br.edu.ifsc.canoinhas.utility.MessageAlert;

public class UpdateProjetoDaemon implements Runnable {

	@Override
	public void run() {

		try {
			ControllerDBProjeto controllerDBProjeto = ControllerDBProjeto.getInstance();
			controllerDBProjeto.getAllProjeto();
			
		} catch (IOException e) {
			MessageAlert.mensagemErro("Erro ao receber dados dos projetos");
			e.printStackTrace();
		}

	}

}
