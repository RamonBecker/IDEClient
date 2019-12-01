package br.edu.ifsc.canoinhas.modelDao.controller.usuario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsc.canoinhas.entities.Usuario;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;

public class DaoDBUsuario {

	private static DaoDBUsuario controllerDBUsuario;
	private List<Usuario> listUsuario;
	private String ipServer = "localhost";
	private int portServer = 1024;

	private DaoDBUsuario() {
		this.listUsuario = new ArrayList<Usuario>();
	}

	public static DaoDBUsuario getInstance() {
		if (controllerDBUsuario == null) {
			controllerDBUsuario = new DaoDBUsuario();
		}
		return controllerDBUsuario;
	}

	public void submitRemoveUsuarioServer(String name, String senha, String operation) {
		try {

			int idUsuario = 0;
			boolean check = false;

			for (Usuario usuario : listUsuario) {
				if (usuario.getName().equals(name) && usuario.getPassword().equals(senha)) {
					idUsuario = usuario.getId();
					check = true;
				}
			}

			if (!check) {
				MessageAlert.mensagemErro("O usuário não existe");
				return;
			}

			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("usuario;" + operation + ";" + idUsuario + ";");
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
			e.printStackTrace();
		}
	}

	public void getAllUsuario() throws UnknownHostException, IOException {

		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("usuario;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		System.out.println(msg);

		if (!msg.contains("404") && msg.length() > 0) {

			String[] splitResult = msg.split(";");
			for (int i = 0; i < splitResult.length - 1; i++) {
				listUsuario.add(new Usuario(Integer.parseInt(splitResult[i]), splitResult[i + 1], splitResult[i + 2]));
				i += 2;
			}
		}

		System.out.println(listUsuario);
		in.close();
		out.close();
		server.close();

	}

	public void submitUsuarioServer(String nome, String senha, String operation) {
		try {

			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("usuario;" + operation + ";" + nome + ";" + senha);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String resposta = in.readUTF();

			if (resposta.contentEquals("ok")) {
				MessageAlert.mensagemRealizadoSucesso(StringUtility.completeOperation);
			} else {
				MessageAlert.mensagemErro(StringUtility.erro);
			}

			in.close();
			out.close();
			server.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void submitEditNameUsuarioServer(String oldNome, String newNome, String senha, String operation) {
		try {

			int idUsuario = 0;
			boolean check = false;

			for (Usuario usuario : listUsuario) {
				if (usuario.getName().equals(oldNome) && usuario.getPassword().equals(senha)) {
					idUsuario = usuario.getId();
					check = true;
				}
			}

			if (!check) {
				MessageAlert.mensagemErro("O usuário não existe");
				return;
			}

			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("usuario;" + operation + ";" + idUsuario + ";" + newNome + ";" + senha);
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
			e.printStackTrace();
		}

	}

	public void submitEditPasswordUsuarioServer(String nome, String senha, String newSenha, String operation) {
		try {

			int idUsuario = 0;
			boolean check = false;

			for (Usuario usuario : listUsuario) {
				if (usuario.getName().equals(nome) && usuario.getPassword().equals(senha)) {
					idUsuario = usuario.getId();
					check = true;
				}
			}

			if (!check) {
				MessageAlert.mensagemErro("O usuário não existe");
				return;
			}

			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("usuario;" + operation + ";" + idUsuario + ";" + nome + ";" + newSenha);
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
			e.printStackTrace();
		}

	}

	public boolean login(String nameUser, String passwordUser) {
		for (Usuario usuario : listUsuario) {
			if (usuario.getName().equals(nameUser) && usuario.getPassword().equals(passwordUser)) {
				return true;
			}
		}
		return false;
	}

	public List<Usuario> getListUsuario() {
		return listUsuario;
	}
}
