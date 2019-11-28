package br.edu.ifsc.canoinhas.db.connection.controller.projeto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.edu.ifsc.canoinhas.entities.Projeto;
import br.edu.ifsc.canoinhas.utility.MessageAlert;

public class DaoDBPacote {

	private String ipServer = "localhost";
	private int portServer = 1024;

	public void getAllProjetoPacote() throws UnknownHostException, IOException {

		//ControllerDBProjeto controllerDBProjeto = ControllerDBProjeto.getInstance();
		Socket server = new Socket(ipServer, portServer);
		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("pacote;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();
//		String[] create = null;
//
		System.out.println(msg);
//
//		if (!msg.contains("404") && msg.length() > 0) {
//
//			String[] splitResult = msg.split("-");
//
//			for (int i = 0; i < splitResult.length; i++) {
//				System.out.print(splitResult[i] + " " + " indice: " + i);
//
//				System.out.println("\n");
//			}
//
//		} else {
//			MessageAlert.mensagemErro("Erro ao receber dados dos projetos");
//		}
//
		in.close();
		out.close();
		server.close();
	}
}
