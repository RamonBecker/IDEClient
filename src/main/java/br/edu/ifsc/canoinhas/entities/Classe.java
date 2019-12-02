package br.edu.ifsc.canoinhas.entities;

import br.edu.ifsc.canoinhas.utility.StringUtility;

public class Classe {

	private int id;
	private String nome;
	private String codigo;
	private String typeClasse;
	private String main;

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

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
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

	public void setCodigoClasse(String main, String typeClass) {
		if (typeClass.equals("public") && main.equals("1")) {

			setCodigo("public class  " + getNome() + "  {" + "\n" + StringUtility.mainClass);
		}
		if (typeClass.equals("private") && main.equals("1")) {

			setCodigo("private class" + getNome() + "   {" + "\n" + StringUtility.mainClass);
		}
	}

	public String getTypeClasse() {
		return typeClasse;
	}

	public void setTypeClasse(String typeClasse) {
		this.typeClasse = typeClasse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + id;
		result = prime * result + ((main == null) ? 0 : main.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((typeClasse == null) ? 0 : typeClasse.hashCode());
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
		if (main == null) {
			if (other.main != null)
				return false;
		} else if (!main.equals(other.main))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (typeClasse == null) {
			if (other.typeClasse != null)
				return false;
		} else if (!typeClasse.equals(other.typeClasse))
			return false;
		return true;
	}
}
