package br.com.senaifood.security.dto;

public class UserDto {

	private Long id;
	private String username;
	private String tipoUsuario;
	private String token;

	public UserDto() {
	}

	public UserDto(Long id, String username, String tipoUsuario, String token) {
		this.id = id;
		this.username = username;
		this.tipoUsuario = tipoUsuario;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
