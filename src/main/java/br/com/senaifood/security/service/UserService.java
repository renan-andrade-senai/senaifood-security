package br.com.senaifood.security.service;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.senaifood.security.client.ClienteClient;
import br.com.senaifood.security.client.EntregadorClient;
import br.com.senaifood.security.client.RestauranteClient;
import br.com.senaifood.security.dto.ClienteDto;
import br.com.senaifood.security.dto.EntregadorDto;
import br.com.senaifood.security.dto.RestauranteDto;
import br.com.senaifood.security.dto.UserDto;
import br.com.senaifood.security.model.TipoUsuario;
import br.com.senaifood.security.model.User;
import br.com.senaifood.security.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private ClienteClient clienteClient;
	@Autowired
	private EntregadorClient entregadorClient;
	@Autowired 
	private RestauranteClient restauranteClient; 
	
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = findByUserName(username);
		return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}
	
	public Optional<User> findByUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
	public Object getDadosUsuario(UserDto userDto) {
		if (StringUtils.isNotBlank(userDto.getTipoUsuario())) {
			try {
				switch (TipoUsuario.fromTipo(userDto.getTipoUsuario())) {
				case CLIENTE: {
					ClienteDto dto = clienteClient.getClientePorId(userDto.getId());
					return dto;
				}
				case RESTAURANTE: {
					RestauranteDto dto = restauranteClient.getRestaurantePorId(userDto.getId());
					return dto;
				}
				case ENTREGADOR: {
					EntregadorDto dto = entregadorClient.getEntregadorPorId(userDto.getId());
					return dto;
				}
				}
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

}
