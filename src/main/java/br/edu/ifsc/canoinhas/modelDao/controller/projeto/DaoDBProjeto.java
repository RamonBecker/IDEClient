package br.edu.ifsc.canoinhas.modelDao.controller.projeto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.edu.ifsc.canoinhas.entities.Classe;
import br.edu.ifsc.canoinhas.entities.Pacote;
import br.edu.ifsc.canoinhas.entities.Projeto;
import br.edu.ifsc.canoinhas.modelDao.Conn;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;

public class DaoDBProjeto {

	private String ipServer = "localhost";
	private int portServer = 1024;
	private List<Projeto> listProjeto;
	private static DaoDBProjeto controllerDBProjeto;

	private DaoDBProjeto() {
		listProjeto = new ArrayList<Projeto>();
	}

	public static DaoDBProjeto getInstance() {
		if (controllerDBProjeto == null) {
			controllerDBProjeto = new DaoDBProjeto();

		}
		return controllerDBProjeto;
	}

	public void submitProjetoServer(String nome, String location, String operation) {
		try {

			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("projeto;" + operation + ";" + nome + ";" + location);
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
			System.out.println("Erro: " + e.getMessage());
		}

	}

	public void submitIdProjectServer(String idProject, String newName, String operation)
			throws UnknownHostException, IOException {

		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("projeto;" + operation + ";" + idProject + ";" + newName);
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

	public void getAllProjeto() throws UnknownHostException, IOException {

		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("projeto;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();
		String[] createProjeto = null;

		System.out.println(msg);

		Pacote auxPacote = null;

		if (!msg.contains("404") && msg.length() > 0) {

			String[] splitResult = msg.split("-");

			for (int i = 0; i < splitResult.length; i++) {
				System.out.print("Projeto:" + splitResult[i] + " " + " indice: " + i);

				System.out.println("\n");

				if (splitResult[i].contains(";")) {
					createProjeto = splitResult[i].split(";");

					Projeto projeto = new Projeto(Integer.parseInt(createProjeto[0]), createProjeto[1],
							createProjeto[2]);

					for (int j = 0; j < createProjeto.length; j++) {
						// System.out.println("Create projeto: " + createProjeto[j]);

						if (createProjeto[j].contains(",")) {
							String[] createPacote = createProjeto[j].split(",");

							for (int k = 0; k < createPacote.length; k++) {

								Pacote pacote = null;
								System.out.println("Create Pacote: " + createPacote[k]);
								if (createPacote[k].contains("/")) {

									String[] createClass = createPacote[k].split("/");

									for (int l = 0; l < createClass.length; l++) {
										System.out.println(createClass[l]);
										Classe classe = new Classe(Integer.parseInt(createClass[l]),
												createClass[l + 1]);

										classe.setTypeClasse(createClass[l + 2]);
										classe.setMain(createClass[l + 3]);
										auxPacote.getListClasse().add(classe);
										l += 3;
									}

								} else {
									pacote = new Pacote(Integer.parseInt(createPacote[k]), createPacote[k + 1]);
									auxPacote = pacote;
									projeto.getListPacote().add(auxPacote);
									k++;
								}
							}
						}

					}

					listProjeto.add(projeto);
				}

			}
		}

		System.out.println(listProjeto);

		in.close();
		out.close();
		server.close();

	}

	public List<Projeto> getListProjeto() {
		return listProjeto;
	}

	public void updateClasse(Projeto projeto, Pacote pacote, String nameClass, Boolean main, String typeClasse) {
		if (projeto != null && pacote != null) {

			EntityManager em = Conn.getEntityManager();
			em.getTransaction().begin();

			Projeto searchProjeto = em.find(Projeto.class, projeto.getId());
			// if (update.equals("Pacote")) {
			Pacote searchPacote = null;

			for (Pacote listPacote : searchProjeto.getListPacote()) {
				if (listPacote.getNome().equals(pacote.getNome())) {
					searchPacote = listPacote;
					break;
				}
			}

			searchPacote.addClass(nameClass, main, typeClasse);

			em.getTransaction().commit();
			em.close();

		} else {
			MessageAlert.mensagemErro(StringUtility.projectNull);
		}
	}

	public void processFirstProjeto() {

		for (Projeto projeto : listProjeto) {
			if (projeto.getNome().equals("Meu projeto de teste")) {
				for (Pacote pacote : projeto.getListPacote()) {
					if (pacote.getNome().equals("Meu pacote de teste")) {
						for (Classe classe : pacote.getListClasse()) {
							if (classe.getNome().equals("Main")) {
								classe.setCodigo(StringUtility.main);
							}

							if (classe.getNome().equals("CadastroEmpresa")) {
								classe.setCodigo(StringUtility.cadastrarEmpresa);
							}

							if (classe.getNome().equals("Login")) {
								classe.setCodigo(StringUtility.login);
							}

							if (classe.getNome().equals("Buscarempresa")) {
								classe.setCodigo(StringUtility.buscarEmpresa);
							}

							if (classe.getNome().equals("Principal")) {
								classe.setCodigo(StringUtility.telaPrincipal);
							}

							if (classe.getNome().equals("RedefinirSenhaUsuario")) {
								classe.setCodigo(StringUtility.redefinirSenhaUsuario);
							}

							if (classe.getNome().equals("Atender Ocorrencia")) {
								classe.setCodigo(StringUtility.classeAtenderOcorrencia);
							}

							try {
								//
								// new DaoDBClasse().submitIDClasseServer(String.valueOf(classe.getId()),
								// "editCodigo");
								new DaoDBClasse().submitCodigoEditClasseServer(String.valueOf(classe.getId()),
										classe.getCodigo(), "editCodigo");

							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	private void addProjeto() {

//		Projeto projeto = new Projeto("Meu projeto de teste", "loca/projeto");
//
//		Pacote pacote = new Pacote("Meu pacote de teste");
//
//		Classe classeCadastroEmpresa = new Classe("CadastroEmpresa");
//		Classe classeLogin = new Classe("Login");
//		Classe classeBuscarEmpresa = new Classe("Buscarempresa");
//		Classe classePrincipal = new Classe("Principal");
//		Classe classeRedefinirSenhaUsuario = new Classe("RedefinirSenhaUsuario");
//		Classe classeAtenderOcorrencia = new Classe("Atender Ocorrencia");
//		Classe classeMain = new Classe("Main");
//
//		classeCadastroEmpresa.setCodigo(StringUtility.cadastrarEmpresa);
//		classeLogin.setCodigo(StringUtility.login);
//		classeBuscarEmpresa.setCodigo(StringUtility.buscarEmpresa);
//		classePrincipal.setCodigo(StringUtility.telaPrincipal);
//		classeRedefinirSenhaUsuario.setCodigo(StringUtility.redefinirSenhaUsuario);
//		classeAtenderOcorrencia.setCodigo(StringUtility.classeAtenderOcorrencia);
//		classeMain.setCodigo(StringUtility.main);
//
//		pacote.setListClasse(new ArrayList<Classe>());
//
//		pacote.addClass(classeCadastroEmpresa);
//		pacote.addClass(classeLogin);
//		pacote.addClass(classeBuscarEmpresa);
//		pacote.addClass(classePrincipal);
//		pacote.addClass(classeRedefinirSenhaUsuario);
//		pacote.addClass(classeAtenderOcorrencia);
//		pacote.addClass(classeMain);
//
//		projeto.setListPacote(new ArrayList<Pacote>());
//
//		projeto.addPackage(pacote);
//
//		addProjectBD(projeto);

	}
}
