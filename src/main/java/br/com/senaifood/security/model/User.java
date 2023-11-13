package br.com.senaifood.security.model;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String email;
	@Column(name = "tipo_usuario")
	private String tipoUsuario;

	public User() {
	}

	public User(Long id, String username, String password, String email, String tipoUsuario) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority authority = null;
		if (StringUtils.isNotBlank(tipoUsuario)) {
			switch (TipoUsuario.fromTipo(tipoUsuario)) {
			case CLIENTE:
				authority = new SimpleGrantedAuthority(TipoUsuario.CLIENTE.getDsTipo());
				break;
			case RESTAURANTE:
				authority = new SimpleGrantedAuthority(TipoUsuario.RESTAURANTE.getDsTipo());
				break;
			case ENTREGADOR:
				authority = new SimpleGrantedAuthority(TipoUsuario.ENTREGADOR.getDsTipo());
				break;
			}
			return List.of(authority);
		}
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
