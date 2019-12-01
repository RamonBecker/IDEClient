package br.edu.ifsc.canoinhas.modelDao.controller.threads;

import java.io.IOException;

import br.edu.ifsc.canoinhas.modelDao.controller.usuario.DaoDBUsuario;

public class UpdateUsuarioServer implements Runnable {

	@Override
	public void run() {

		try {
			DaoDBUsuario daoDBUsuario = DaoDBUsuario.getInstance();
			daoDBUsuario.getListUsuario().clear();
			daoDBUsuario.getAllUsuario();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
