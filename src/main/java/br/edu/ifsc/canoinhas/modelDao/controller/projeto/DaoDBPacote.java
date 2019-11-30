package br.edu.ifsc.canoinhas.modelDao.controller.projeto;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;

public class DaoDBPacote {
	
	private String ipServer = "localhost";
	private int portServer = 1024;
	
	public void submitPacoteServer(String idProjeto, String nomePacote, String operation) {

		try {

			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("pacote;" + operation + ";" + idProjeto + ";" + nomePacote);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String resposta = in.readUTF();

			if (resposta.contentEquals("Ok")) {
				MessageAlert.mensagemRealizadoSucesso(StringUtility.completeOperation);
			} else {
				MessageAlert.mensagemErro(StringUtility.erro);
			}

			in.close();
			out.close();
			server.close();

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
