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
		 
		if(msg.contains("Ok")) {
			MessageAlert.mensagemRealizadoSucesso(StringUtility.completeOperation);
		}else {
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
		// String[] createPacote = null;
		// String[] createClass = null;
		String[] resultPacote = null;
		String teste = "";

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
						System.out.println("Create projeto: " + createProjeto[j]);

						if (createProjeto[j].contains(",")) {
							String[] createPacote = createProjeto[j].split(",");

							for (int k = 0; k < createPacote.length; k++) {

								Pacote pacote = null;
								System.out.println("Create Pacote: " + createPacote[k]);
								if (createPacote[k].contains("/")) {

									String[] createClass = createPacote[k].split("/");

									for (int l = 0; l < createClass.length; l++) {
										System.out.println("Create Class" + createClass[l]);
										Classe classe = new Classe(Integer.parseInt(createClass[l]),
												createClass[l + 1]);
										auxPacote.getListClasse().add(classe);
										l++;
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

	public void addProjeto(String nameProject, String localProject) {

		EntityManager em = Conn.getEntityManager();

		try {

			Projeto projetoNew = new Projeto(nameProject, localProject);

			if (checkProjeto(nameProject.trim(), localProject.trim())) {
				MessageAlert.mensagemErro(StringUtility.projetoIguais);
				return;
			}

			addProjectBD(projetoNew);
			MessageAlert.mensagemRealizadoSucesso(StringUtility.projectCreate);

		} catch (IllegalArgumentException e) {
			MessageAlert.mensagemErro(e.getMessage());
		} finally {
			em.close();
		}
	}

	private void addProjectBD(Projeto projeto) {

		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(projeto);
		em.getTransaction().commit();
		em.close();

	}

	private void loadListProjeto() {

		EntityManager em = Conn.getEntityManager();

		listProjeto = em.createQuery("SELECT PT FROM Projeto AS PT", Projeto.class).getResultList();

		System.out.println(listProjeto);
		em.close();
	}

	private Boolean checkProjeto(String nameProject, String localProject) {

		loadListProjeto();

		if (!listProjeto.isEmpty()) {
			for (Projeto projeto : listProjeto) {
				if (projeto.getNome().equals(nameProject) && projeto.getLocation().equals(localProject)) {
					return true;
				}
			}
		}

		return false;
	}

	public List<Projeto> getListProjeto() {
		return listProjeto;
	}

	public void addPackageToProject(Projeto projeto, String namePackage) {

		if (projeto != null) {
			System.out.println(projeto);
			for (int i = 0; i < projeto.getListPacote().size(); i++) {
				if (projeto.getListPacote().get(i).getNome().equals(namePackage)) {
					MessageAlert.mensagemErro(StringUtility.packageExisting);
					return;
				}
			}

			Pacote pacote = new Pacote(namePackage);
			addProject(projeto, pacote);

			MessageAlert.mensagemRealizadoSucesso(StringUtility.pacoteCreate);
		} else {
			MessageAlert.mensagemErro(StringUtility.projectNull);
		}

	}

	public void addProject(Projeto projeto, Pacote pacote) {

		if (projeto != null && pacote != null) {

			EntityManager em = Conn.getEntityManager();
			em.getTransaction().begin();

			Projeto searchProjeto = em.find(Projeto.class, projeto.getId());
			searchProjeto.getListPacote().add(pacote);
			em.getTransaction().commit();
			em.close();

		} else {
			MessageAlert.mensagemErro(StringUtility.projectNull);
		}
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

	public void editProject(Projeto projeto) {
		EntityManager em = Conn.getEntityManager();

		em.getTransaction().begin();

		Projeto projetoSearch = em.find(Projeto.class, projeto.getId());
		projetoSearch.setNome(projeto.getNome());

		em.getTransaction().commit();
		em.close();
	}

	public void editPacote(Pacote pacote) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();

		Pacote pacoteSeach = em.find(Pacote.class, pacote.getId());

		pacoteSeach.setNome(pacote.getNome());

		em.getTransaction().commit();
		em.close();
	}

	public void editClass(Classe classe) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();

		Classe classeSearch = em.find(Classe.class, classe.getId());

		classeSearch.setNome(classe.getNome());
		classeSearch.setCodigo(classe.getCodigo());

		em.getTransaction().commit();
		em.close();
	}

	public void removeProject(Projeto projeto) {
		EntityManager em = Conn.getEntityManager();

		em.getTransaction().begin();

		Projeto projetoSearch = em.find(Projeto.class, projeto.getId());

		em.remove(projetoSearch);

		em.getTransaction().commit();
		em.close();
	}

	public void removePacote(Pacote pacote) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();

		Pacote pacoteSearch = em.find(Pacote.class, pacote.getId());

		em.remove(pacoteSearch);

		em.getTransaction().commit();
		em.close();
	}

	public void removeClass(Classe classe) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();

		Classe classeSearch = em.find(Classe.class, classe.getId());

		em.remove(classeSearch);

		em.getTransaction().commit();
		em.close();
	}

}
