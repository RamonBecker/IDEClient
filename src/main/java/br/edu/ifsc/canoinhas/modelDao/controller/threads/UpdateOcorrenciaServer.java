package br.edu.ifsc.canoinhas.modelDao.controller.threads;

import java.io.IOException;

import br.edu.ifsc.canoinhas.modelDao.controller.ocorrencia.DaoDBOcorrencia;

public class UpdateOcorrenciaServer implements Runnable{

	@Override
	public void run() {
		
		try {
			DaoDBOcorrencia daoDBOcorrencia = DaoDBOcorrencia.getInstance();
			daoDBOcorrencia.getListOcorrencias().clear();
			daoDBOcorrencia.getAllOcorrencia();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
