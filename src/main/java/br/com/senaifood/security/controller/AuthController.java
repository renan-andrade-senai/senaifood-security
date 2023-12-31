package br.com.senaifood.security.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaifood.security.dto.UserDto;
import br.com.senaifood.security.dto.UserRequest;
import br.com.senaifood.security.model.TipoUsuario;
import br.com.senaifood.security.model.User;
import br.com.senaifood.security.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<Long> addNewUser(@RequestBody User user) {
		try {
			User u = userService.saveUser(user);
			return ResponseEntity.ok(u.getId());
		} catch (DuplicateKeyException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> getToken(@RequestBody UserRequest user) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (authentication.isAuthenticated()) {
			User usuario = (User) authentication.getPrincipal();
			UserDto userDto = new UserDto(usuario.getId(), user.getUsername(), usuario.getTipoUsuario(), userService.generateToken(user.getUsername()));
//			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//			if (authorities != null && !authorities.isEmpty()) {
//				Optional<? extends GrantedAuthority> authority = authorities.stream().reduce((result, element) -> element);
//				authority.ifPresent((a) -> userDto.setTipoUsuario(TipoUsuario.fromDesc(a.getAuthority())));
//			}
			Object dadosUsuario = userService.getDadosUsuario(userDto);
			HashMap<String, Object> retorno = new HashMap<>(2);
			retorno.put("user", userDto);
			retorno.put("dados", dadosUsuario);
			return ResponseEntity.ok(retorno);
		} else {
			throw new RuntimeException("Acesso inválido");
		}
	}
	
	@GetMapping("/validate")
	public void validateToken(@RequestParam("token") String token) {
		userService.validateToken(token);
	}
	
	@GetMapping("/usertoken")
	public ResponseEntity<?> getUserFromToken(@RequestParam("token") String token) {
		User user = userService.findUserFromToken(token);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getTipoUsuario(), token);
		Object dadosUsuario = userService.getDadosUsuario(userDto);
		HashMap<String, Object> retorno = new HashMap<>(2);
		retorno.put("user", userDto);
		retorno.put("dados", dadosUsuario);
		return ResponseEntity.ok(retorno);
	}

}
