package br.edu.ifsc.canoinhas.modelDao.controller.ocorrencia;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsc.canoinhas.entities.Endereco;
import br.edu.ifsc.canoinhas.entities.Ocorrencia;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;

public class DaoDBOcorrencia {

	private String ipServer = "localhost";
	private int portServer = 1024;
	private static DaoDBOcorrencia controllerDBOcorrencia;
	private List<Ocorrencia> listOcorrencias;

	private DaoDBOcorrencia() {
		listOcorrencias = new ArrayList<Ocorrencia>();
	}

	public static DaoDBOcorrencia getInstance() {
		if (controllerDBOcorrencia == null) {
			controllerDBOcorrencia = new DaoDBOcorrencia();
		}
		return controllerDBOcorrencia;
	}

	public void getAllOcorrencia() throws UnknownHostException, IOException {

		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("ocorrencia;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		System.out.println(msg);

		if (!msg.contains("404") && msg.length() > 0) {

			String[] splitResult = msg.split("-");

			for (int i = 0; i < splitResult.length; i++) {
				if (splitResult[i].contains(";")) {
					String[] createOcorrencia = splitResult[i].split(";");

					Endereco endereco = new Endereco(Integer.parseInt(createOcorrencia[4]), createOcorrencia[5],
							createOcorrencia[6], createOcorrencia[7], createOcorrencia[8], createOcorrencia[9],
							createOcorrencia[10], createOcorrencia[11], "");
					Ocorrencia ocorrencia = new Ocorrencia(Integer.parseInt(createOcorrencia[0]), createOcorrencia[1],
							createOcorrencia[2], createOcorrencia[3], endereco);

					listOcorrencias.add(ocorrencia);

				}
			}
		}
		in.close();
		out.close();
		server.close();

	}

	public void submitOcorrenciaServer(String nomeVitima, String gravidae, String rua, String bairro, String numero,
			String telefone, String complemento, String cep, String cidade, String operation) {
		try {

			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());

			out.writeUTF("ocorrencia;" + operation + ";" + nomeVitima + ";" + gravidae + ";" + rua + ";" + bairro + ";"
					+ numero + ";" + telefone + ";" + complemento + ";" + cep + ";" + cidade);
			out.flush();
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

	public void submitEditStatusOcorrenciaServer(String idOcorrencia, String status, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("ocorrencia;" + operation + ";" + idOcorrencia + ";" + status);
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

	public List<Ocorrencia> getListOcorrencias() {
		return listOcorrencias;
	}
}
