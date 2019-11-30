package br.edu.ifsc.canoinhas.entities;

import br.edu.ifsc.canoinhas.utility.StringUtility;

public class Classe {

	private int id;
	private String nome;
	private String codigo;
	private String typeClasse;

	public Classe() {
	}

	public Classe(int id, String nome) {
		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomeClasseVazio);
		}
		this.id = id;
		this.nome = nome;
	}

	public Classe(String nome, String codigo) {

		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomeClasseVazio);
		}
		this.nome = nome;
		this.codigo = codigo;
	}

	public Classe(String nome) {
		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomeClasseVazio);
		}
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomeClasseVazio);
		}
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		if (codigo.isEmpty() || codigo == null) {
			throw new IllegalArgumentException(StringUtility.codigoVazioClasse);
		}
		this.codigo = codigo;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Classe [id=" + id + ", nome=" + nome + ", codigo=" + codigo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + id;
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
		Classe other = (Classe) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void setCodigoClasse(Boolean main, String typeClass) {
		if (typeClass.equals("public") && main) {

			setCodigo("public class  " + getNome() + "  {" + "\n" + StringUtility.mainClass);
		}
		if (typeClass.equals("private") && main) {

			setCodigo("private class" + getNome() + "   {" + "\n" + StringUtility.mainClass);
		}
	}

	public String getTypeClasse() {
		return typeClasse;
	}

	public void setTypeClasse(String typeClasse) {
		this.typeClasse = typeClasse;
	}

}
