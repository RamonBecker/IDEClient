package br.edu.ifsc.canoinhas.modelDao.controller.projeto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;

public class DaoDBClasse {

	private String ipServer = "localhost";
	private int portServer = 1024;

	public void submitClasseServer(String idPacote, String nomeClass, Boolean main, String typeClass,
			String operation) {
		try {

			String mainPrincipal = "";

			if (main) {
				mainPrincipal = "1";
			} else {
				mainPrincipal = "0";
			}
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF(
					"classe;" + operation + ";" + idPacote + ";" + nomeClass + ";" + typeClass + ";" + mainPrincipal);
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

	public void submitNameEditClasseServer(String idClasse, String newName, String operation)
			throws UnknownHostException, IOException {

		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("classe;" + operation + ";" + idClasse + ";" + newName);
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (msg.contains("Ok")) {
			MessageAlert.mensagemRealizadoSucesso(StringUtility.completeOperation);
		} else {
			MessageAlert.mensagemErro(StringUtility.erro);
		}

		in.close();
		out.close();
		server.close();
	}

	public void submitCodigoEditClasseServer(String idClasse, String codigo, String operation)
			throws UnknownHostException, IOException {

		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());

		out.writeUTF("classe-" + operation + "-" + idClasse + "-" + codigo);
		out.flush();

		out.close();
		server.close();
	}

	public void submitIDClasseServer(String idClasse, String operation) throws UnknownHostException, IOException {
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());

		out.writeUTF("classe;" + operation + ";" + idClasse);
		out.flush();

		// ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		// String msg = in.readUTF();

//		if (msg.contains("Ok")) {
//			MessageAlert.mensagemRealizadoSucesso(StringUtility.completeOperation);
//		} else {
//			MessageAlert.mensagemErro(StringUtility.erro);
//		}
//
//		in.close();

		out.close();
		server.close();
	}
}
