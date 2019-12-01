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

		System.out.println(msg);

		in.close();
		out.close();
		server.close();

	}
	
	public void submitUsuarioServer(String nome, String cnpj, String rua, 
			String bairro, String numero, String telefone,
			String estado, String cep, String cidade,String operation) {
		try {

			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("empresa;" + operation + ";" + nome + ";"
					+cnpj+";"+rua+";"+bairro+";"+numero+";"+telefone+";"
					+estado+";"+cep+";"+cidade
					);
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
	
	public void addEmpresa(Empresa empresa) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteEmpresa(Empresa empresa) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		Empresa empresaSearch = em.find(Empresa.class, empresa.getId());
		em.remove(empresaSearch);
		em.getTransaction().commit();
		em.close();
	}

	public void updateEmpresa(Empresa empresa) {

		EntityManager em = Conn.getEntityManager();

		em.getTransaction().begin();

		Empresa empresaSearch = em.find(Empresa.class, empresa.getId());

		empresaSearch.setCnpj(empresa.getCnpj());
		empresaSearch.setNome(empresa.getNome());

		empresaSearch.getEndereco().setBairro(empresa.getEndereco().getBairro());
		empresaSearch.getEndereco().setCep(empresa.getEndereco().getCep());
		empresaSearch.getEndereco().setCidade(empresa.getEndereco().getCidade());
		empresaSearch.getEndereco().setEstado(empresa.getEndereco().getEstado());
		empresaSearch.getEndereco().setNumero(empresa.getEndereco().getNumero());
		empresaSearch.getEndereco().setRua(empresa.getEndereco().getRua());
		empresaSearch.getEndereco().setTelefone(empresa.getEndereco().getTelefone());

		System.out.println("Empresa BD:" + empresaSearch);
		em.getTransaction().commit();
		em.close();
	}

	public List<Empresa> getListEmpresa() {
		return listEmpresa;
	}

}