package br.edu.ifsc.canoinhas.db.connection.controller;

import java.util.List;

import javax.persistence.EntityManager;
import br.edu.ifsc.canoinhas.db.connection.Conn;
import br.edu.ifsc.canoinhas.entities.Empresa;

public class ControllerDBEmpresa {
	private static ControllerDBEmpresa controllerDBEmpresa;
	private List<Empresa> listEmpresa;

	public static ControllerDBEmpresa getInstance() {
		if (controllerDBEmpresa == null) {
			controllerDBEmpresa = new ControllerDBEmpresa();
		}
		return controllerDBEmpresa;
	}

	public void addEmpresa(Empresa empresa) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
		em.close();
	}

	private void loadDBListEmpresa() {
		EntityManager em = Conn.getEntityManager();
		listEmpresa = em.createQuery("SELECT e FROM Empresa AS e", Empresa.class).getResultList();
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
		
		System.out.println("Empresa BD:"+empresaSearch);
		em.getTransaction().commit();
		em.close();
	}

	public List<Empresa> getListEmpresa() {
		loadDBListEmpresa();
		return listEmpresa;
	}

}