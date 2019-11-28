package br.edu.ifsc.canoinhas.db.connection.controller;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.ifsc.canoinhas.db.connection.Conn;
import br.edu.ifsc.canoinhas.entities.Ocorrencia;

public class DaoDBOcorrencia {
	private static DaoDBOcorrencia controllerDBOcorrencia;
	private List<Ocorrencia> listOcorrencias;

	private DaoDBOcorrencia() {
	}

	public static DaoDBOcorrencia getInstance() {
		if (controllerDBOcorrencia == null) {
			controllerDBOcorrencia = new DaoDBOcorrencia();
		}
		return controllerDBOcorrencia;
	}

	public void addOcorrencia(Ocorrencia ocorrencia) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(ocorrencia);
		em.getTransaction().commit();
		em.close();
	}

	public void loadOcorrenciaBD() {
		EntityManager em = Conn.getEntityManager();
		listOcorrencias = em.createQuery("FROM Ocorrencia", Ocorrencia.class).getResultList();
		em.close();
	}

	public void editOcorrencia(Ocorrencia ocorrencia) {

		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		Ocorrencia ocorrenciaSearch = em.find(Ocorrencia.class, ocorrencia.getId());
		ocorrenciaSearch.setStatus(ocorrencia.getStatus());
		em.getTransaction().commit();
		em.close();

	}

	public List<Ocorrencia> getListOcorrencias() {
		loadOcorrenciaBD();
		return listOcorrencias;
	}

}
