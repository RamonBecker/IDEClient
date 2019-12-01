package br.edu.ifsc.canoinhas.modelDao.controller.threads;

import java.io.IOException;

import br.edu.ifsc.canoinhas.modelDao.controller.empresa.DaoDBEmpresa;

public class UpdateEmpresaServer implements Runnable {

	@Override
	public void run() {

		try {
			DaoDBEmpresa daoDBEmpresa = DaoDBEmpresa.getInstance();
			daoDBEmpresa.getListEmpresa().clear();
			daoDBEmpresa.getAllEmpresa();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
