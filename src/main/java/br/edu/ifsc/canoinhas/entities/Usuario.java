package br.edu.ifsc.canoinhas.entities;

import br.edu.ifsc.canoinhas.utility.StringUtility;

public class Usuario {

	private int id;
	private String name;
	private String password;

	public Usuario() {
	}

	public Usuario(String name, String password) {

		if (name.isEmpty() || name == null) {
			throw new IllegalArgumentException(StringUtility.nomeUsuarioVazio);
		}

		if (password.isEmpty() || password == null) {
			throw new IllegalArgumentException(StringUtility.senhaVazio);
		}
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.isEmpty() || name == null) {
			throw new IllegalArgumentException(StringUtility.nomeUsuarioVazio);
		}
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

		if (password.isEmpty() || password == null) {
			throw new IllegalArgumentException(StringUtility.senhaVazio);
		}
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
