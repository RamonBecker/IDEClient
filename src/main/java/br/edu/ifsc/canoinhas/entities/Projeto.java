package br.edu.ifsc.canoinhas.entities;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.canoinhas.utility.StringUtility;

public class Projeto {

	private int id;
	private String nome;
	private String location;

	private List<Pacote> listPacote;

	public Projeto(int id, String nome, String location) {

		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomeProjetoVazio);
		}

		if (location.isEmpty() || location == null) {
			throw new IllegalArgumentException(StringUtility.localDefaultVazio);
		}

		this.id = id;
		this.nome = nome;
		this.location = location;
		this.listPacote = new ArrayList<Pacote>();
	}

	public Projeto(String nome, String location) {

		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomeProjetoVazio);
		}

		if (location.isEmpty() || location == null) {
			throw new IllegalArgumentException(StringUtility.localDefaultVazio);
		}
		this.nome = nome;
		this.location = location;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomePacoteVazio);
		}

		this.nome = nome;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		if (location.isEmpty() || location == null) {
			throw new IllegalArgumentException(StringUtility.localDefaultVazio);
		}

		this.location = location;
	}

	public int getId() {
		return id;
	}

	public List<Pacote> getListPacote() {
		return listPacote;
	}

	public void setListPacote(List<Pacote> listPacote) {
		this.listPacote = listPacote;
	}

	public void addPackage(Pacote pacote) {
		listPacote.add(pacote);
	}

	@Override
	public String toString() {
		return "Projeto [id=" + id + ", nome=" + nome + ", location=" + location + ", listPacote=" + listPacote + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((listPacote == null) ? 0 : listPacote.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (id != other.id)
			return false;
		if (listPacote == null) {
			if (other.listPacote != null)
				return false;
		} else if (!listPacote.equals(other.listPacote))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
