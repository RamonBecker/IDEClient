package br.edu.ifsc.canoinhas.modelDao.controller.empresa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.edu.ifsc.canoinhas.entities.Empresa;
import br.edu.ifsc.canoinhas.entities.Endereco;
import br.edu.ifsc.canoinhas.modelDao.Conn;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;

public class DaoDBEmpresa {

	private String ipServer = "localhost";
	private int portServer = 1024;
	private static DaoDBEmpresa controllerDBEmpresa;
	private List<Empresa> listEmpresa;

	private DaoDBEmpresa() {
		this.listEmpresa = new ArrayList<Empresa>();
	}

	public static DaoDBEmpresa getInstance() {
		if (controllerDBEmpresa == null) {
			controllerDBEmpresa = new DaoDBEmpresa();
		}
		return controllerDBEmpresa;
	}

	public void getAllEmpresa() throws UnknownHostException, IOException {

		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("empresa;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		String[] resultMSG = msg.split("-");

		for (int i = 0; i < resultMSG.length; i++) {
			if (resultMSG[i].contains(";")) {
				String[] createEmpresa = resultMSG[i].split(";");

				Endereco endereco = new Endereco(Integer.parseInt(createEmpresa[3]), createEmpresa[4], createEmpresa[5],
						createEmpresa[6], createEmpresa[7], createEmpresa[8], createEmpresa[9], createEmpresa[10]);
				Empresa empresa = new Empresa(Integer.parseInt(createEmpresa[0]), createEmpresa[1], createEmpresa[2],
						endereco);
				listEmpresa.add(empresa);
			}
		}

		System.out.println(msg);

		in.close();
		out.close();
		server.close();

	}

	public void submitRemoveEmpresaServer(String idEmpresa, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("empresa;" + operation + ";" + idEmpresa);
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

	public void submitEmpresaServer(String nome, String cnpj, String rua, String bairro, String numero, String telefone,
			String estado, String cep, String cidade, String operation) {
		try {

			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("empresa;" + operation + ";" + nome + ";" + cnpj + ";" + rua + ";" + bairro + ";" + numero
					+ ";" + telefone + ";" + estado + ";" + cep + ";" + cidade);
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

	public void submitEmpresaServer(String id, String nome, String cnpj, String rua, String bairro, String numero,
			String telefone, String estado, String cep, String cidade, String operation) {
		try {

			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("empresa;" + operation + ";" + id + ";" + nome + ";" + cnpj + ";" + rua + ";" + bairro + ";"
					+ numero + ";" + telefone + ";" + estado + ";" + cep + ";" + cidade);
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


	public List<Empresa> getListEmpresa() {
		return listEmpresa;
	}

}