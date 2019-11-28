package br.edu.ifsc.canoinhas.db.connection.controller;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.ifsc.canoinhas.db.connection.Conn;
import br.edu.ifsc.canoinhas.entities.Usuario;
import br.edu.ifsc.canoinhas.utility.MessageAlert;
import br.edu.ifsc.canoinhas.utility.StringUtility;

public class DaoDBUsuario {

	private static DaoDBUsuario controllerDBUsuario;
	private List<Usuario> listUsuario;

	public DaoDBUsuario() {
	}

	public static DaoDBUsuario getInstance() {
		if (controllerDBUsuario == null) {
			controllerDBUsuario = new DaoDBUsuario();
		}
		return controllerDBUsuario;
	}

	public void loadUserBD() {
		EntityManager em = Conn.getEntityManager();
		listUsuario = em.createQuery("FROM Usuario", Usuario.class).getResultList();
		em.close();
	}

	public void addUsuario(Usuario usuario) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
		MessageAlert.mensagemRealizadoSucesso(StringUtility.createUser);
	}

	public void updateUsuario(Usuario usuario) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		
		Usuario searchUser = em.find(Usuario.class, usuario.getId());
		
		searchUser.setName(usuario.getName());
		searchUser.setPassword(usuario.getPassword());
		em.getTransaction().commit();
		em.close();
	}

	public void alterNameUsuario(String newName, String oldName, String password) {
		loadUserBD();

		Usuario user = null;

		for (Usuario usuario : listUsuario) {
			if (usuario.getName().equals(oldName) && usuario.getPassword().equals(password)) {
				user = usuario;
				break;
			}
		}

		if (user != null) {
			user.setName(newName);
			updateUsuario(user);
			MessageAlert.mensagemRealizadoSucesso(StringUtility.alterUser);
		} else {
			MessageAlert.mensagemErro(StringUtility.loginIncorret);
		}
	}

	public void alterPassword(String nameUser, String oldPassword, String newPassword) {
		loadUserBD();
		Usuario user = null;

		for (Usuario usuario : listUsuario) {
			if (usuario.getName().equals(nameUser) && usuario.getPassword().equals(oldPassword)) {
				user = usuario;
				break;
			}
		}

		if (user != null) {
			user.setPassword(newPassword);
			updateUsuario(user);
			MessageAlert.mensagemRealizadoSucesso(StringUtility.alterPass);
		} else {
			MessageAlert.mensagemErro(StringUtility.loginIncorret);
		}
	}

	public boolean login(String nameUser, String passwordUser) {
		
		loadUserBD();

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
