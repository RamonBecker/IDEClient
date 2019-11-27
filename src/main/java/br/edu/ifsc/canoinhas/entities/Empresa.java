package br.edu.ifsc.canoinhas.entities;

import br.edu.ifsc.canoinhas.utility.StringUtility;


public class Empresa {

	private int id;
	private String nome;
	private String cnpj;

	private Endereco endereco;

	public Empresa() {
	}

	public Empresa(String nome, String cnpj, Endereco endereco) {

		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomeVazio);
		}

		if (cnpj.isEmpty() || cnpj == null) {
			throw new IllegalArgumentException(StringUtility.cnpjVazio);
		}

		if (endereco == null) {
			throw new IllegalArgumentException(StringUtility.enderecoVazio);
		}

		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		if (endereco == null) {
			throw new IllegalArgumentException(StringUtility.enderecoVazio);
		}

		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException(StringUtility.nomeVazio);
		}
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if (cnpj.isEmpty() || cnpj == null) {
			throw new IllegalArgumentException(StringUtility.cnpjVazio);
		}
		this.cnpj = cnpj;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", endereco=" + endereco + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
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
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
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

}
