package br.edu.ifsc.canoinhas.modelDao.controller.threads;

import java.io.IOException;

import br.edu.ifsc.canoinhas.modelDao.controller.projeto.DaoDBProjeto;

public class UpdateProjetoServer implements Runnable {

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
