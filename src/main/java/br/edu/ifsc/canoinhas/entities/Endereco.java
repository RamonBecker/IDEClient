package br.edu.ifsc.canoinhas.entities;

import br.edu.ifsc.canoinhas.utility.StringUtility;


public class Endereco {

	private int id;
	private String rua, bairro;
	private String numero, telefone, complemento;
	private String estado, cep, cidade;

	public Endereco() {
	}

	public Endereco(int id,String rua, String bairro, String numero, String telefone, String complemento, String cep,
			String cidade, String teste) {

		if (rua.isEmpty() || rua.isBlank()) {
			throw new IllegalArgumentException(StringUtility.ruaVazio);
		}

		if (bairro.isEmpty() || bairro.isBlank()) {
			throw new IllegalArgumentException(StringUtility.bairroVazio);
		}
		if (numero.isEmpty() || numero.isBlank()) {
			throw new IllegalArgumentException(StringUtility.numeroVazio);
		}
		if (telefone.isEmpty() || telefone.isBlank()) {
			throw new IllegalArgumentException(StringUtility.telefoneVazio);
		}

		if (complemento.isEmpty() || complemento.isBlank()) {
			throw new IllegalArgumentException(StringUtility.complementoVazio);
		}

		if (cep.isEmpty() || cep.isBlank()) {
			throw new IllegalArgumentException(StringUtility.cepVazio);
		}

		if (cidade.isEmpty() || cidade.isBlank()) {
			throw new IllegalArgumentException(StringUtility.cidadeVazio);
		}
		
		this.id = id;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.telefone = telefone;
		this.complemento = complemento;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = "Santa Catarina";

	}

	public Endereco(int id,String rua, String bairro, String numero, String estado, String cep, String cidade, String telefone) {

		if (rua.isEmpty() || rua == null) {
			throw new IllegalArgumentException(StringUtility.ruaVazio);
		}

		if (bairro.isEmpty() || bairro == null) {
			throw new IllegalArgumentException(StringUtility.bairroVazio);
		}

		if (cidade.isEmpty() || cidade == null) {
			throw new IllegalArgumentException(StringUtility.cidadeVazio);
		}

		if (estado.isEmpty() || estado == null) {
			throw new IllegalArgumentException(StringUtility.estadoVazio);
		}

		if (cep.isEmpty() || cep == null) {
			throw new IllegalArgumentException(StringUtility.cepVazio);
		}

		if (numero.isEmpty() || numero == null) {
			throw new IllegalArgumentException(StringUtility.numeroVazio);
		}

		if (telefone.isEmpty() || telefone.isBlank()) {
			throw new IllegalArgumentException(StringUtility.telefoneVazio);
		}

		this.id = id;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.estado = estado;
		this.cep = cep;
		this.cidade = cidade;
		this.telefone = telefone;

	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		if (complemento.isEmpty() || complemento == null) {
			throw new IllegalArgumentException(StringUtility.complementoVazio);
		}
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		if (cidade.isEmpty() || cidade == null) {
			throw new IllegalArgumentException(StringUtility.cidadeVazio);
		}
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		if (estado.isEmpty() || estado == null) {
			throw new IllegalArgumentException(StringUtility.estadoVazio);
		}
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if (cep.isEmpty() || cep == null) {
			throw new IllegalArgumentException(StringUtility.cepVazio);
		}

		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		if (rua.isEmpty() || rua == null) {
			throw new IllegalArgumentException(StringUtility.ruaVazio);
		}

		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		if (bairro.isEmpty() || bairro == null) {
			throw new IllegalArgumentException(StringUtility.bairroVazio);
		}
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {

		if (numero.isEmpty() || numero == null) {
			throw new IllegalArgumentException(StringUtility.numeroVazio);
		}
		this.numero = numero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		if (telefone.isEmpty() || telefone == null) {
			throw new IllegalArgumentException(StringUtility.telefoneVazio);
		}

		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "rua:" + rua + ",Bairro:" + bairro + ",Numero:" + numero + ",Telefone:"
				+ telefone + ",Complemento:" + complemento + ",Estado:" + estado + ",Cep:" + cep + ",Cidade:"
				+ cidade + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + id;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id != other.id)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}
