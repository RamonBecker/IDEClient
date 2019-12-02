package br.edu.ifsc.canoinhas.entities;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.canoinhas.utility.StringUtility;

public class Pacote {

	private int id;
	private String nome;
	private List<Classe> listClasse;

	public Pacote() {
	}

	public Pacote(String nome) {
		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomePacoteVazio);
		}
		this.nome = nome;
	}

	public Pacote(int id, String nome) {
		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomePacoteVazio);
		}
		this.id = id;
		this.nome = nome;
		this.listClasse = new ArrayList<Classe>();
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

	public int getId() {
		return id;
	}

	public void setListClasse(List<Classe> listClasse) {
		this.listClasse = listClasse;
	}

	public List<Classe> getListClasse() {
		return listClasse;
	}

	public void addClass(String nome, Boolean main, String typeClass) {

		Classe classe = new Classe(nome);

		setCodigoClasse(main, typeClass, classe);

		listClasse.add(classe);
	}

	public void setCodigoClasse(Boolean main, String typeClass, Classe classe) {
		if (typeClass.equals("public") && main) {
			classe.setTypeClasse(typeClass);
			classe.setCodigo("public class  " + classe.getNome() + "  {" + "\n" + StringUtility.mainClass);
		}
		if (typeClass.equals("private") && main) {
			classe.setTypeClasse(typeClass);
			classe.setCodigo("private class" + classe.getNome() + "   {" + "\n" + StringUtility.mainClass);
		}
	}

	public void addClass(Classe classe) {
		listClasse.add(classe);
	}

	@Override
	public String toString() {
		return "Pacote [id=" + id + ", nome=" + nome + ", listClasse=" + listClasse + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((listClasse == null) ? 0 : listClasse.hashCode());
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
		Pacote other = (Pacote) obj;
		if (id != other.id)
			return false;
		if (listClasse == null) {
			if (other.listClasse != null)
				return false;
		} else if (!listClasse.equals(other.listClasse))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
