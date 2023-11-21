package br.com.senaifood.security.dto;

public class EntregadorDto {

	private int id;
	private long idUsuario;
	private String nome;
	private String cpf;

	public EntregadorDto() {
	}

	public EntregadorDto(int id, long idUsuario, String nome, String cpf) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
