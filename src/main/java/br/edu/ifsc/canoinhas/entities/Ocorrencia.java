package br.edu.ifsc.canoinhas.entities;


import br.edu.ifsc.canoinhas.utility.StringUtility;


public class Ocorrencia {

	private int id;
	private String nomeVitima;
	private String gravidade;
	private String status;

	private Endereco endereco;

	public Ocorrencia() {
	}

	public Ocorrencia(int id,String nomeVitima, String gravidade, String status, Endereco endereco) {

		if (nomeVitima.isEmpty() || nomeVitima.isBlank()) {
			throw new IllegalArgumentException(StringUtility.nomeVitimaVazio);
		}

		if (gravidade.isEmpty() || gravidade.isBlank()) {
			throw new IllegalArgumentException(StringUtility.gravidadeVazio);
		}

		if (endereco == null) {
			throw new IllegalArgumentException(StringUtility.enderecoVazio);
		}
		this.id = id;
		this.nomeVitima = nomeVitima;
		this.gravidade = gravidade;
		this.endereco = endereco;
		this.status = status;
	}

	public String getNomeVitima() {
		return nomeVitima;
	}

	public void setNomeVitima(String nomeVitima) {
		if (nomeVitima.isEmpty() || nomeVitima.isBlank()) {
			throw new IllegalArgumentException(StringUtility.nomeVitimaVazio);
		}
		this.nomeVitima = nomeVitima;
	}

	public String getGravidade() {
		return gravidade;
	}

	public void setGravidade(String gravidade) {

		if (gravidade.isEmpty() || gravidade.isBlank()) {
			throw new IllegalArgumentException(StringUtility.gravidadeVazio);
		}

		this.gravidade = gravidade;
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

	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {

		if (status.isEmpty() || status == null) {
			throw new IllegalArgumentException(StringUtility.statusVazio);
		}
		this.status = status;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((gravidade == null) ? 0 : gravidade.hashCode());
		result = prime * result + id;
		result = prime * result + ((nomeVitima == null) ? 0 : nomeVitima.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Ocorrencia other = (Ocorrencia) obj;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (gravidade == null) {
			if (other.gravidade != null)
				return false;
		} else if (!gravidade.equals(other.gravidade))
			return false;
		if (id != other.id)
			return false;
		if (nomeVitima == null) {
			if (other.nomeVitima != null)
				return false;
		} else if (!nomeVitima.equals(other.nomeVitima))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ocorrencia [id=" + id + ", nomeVitima=" + nomeVitima + ", gravidade=" + gravidade + ", status=" + status
				+ ", endereco=" + endereco + "]";
	}
}
