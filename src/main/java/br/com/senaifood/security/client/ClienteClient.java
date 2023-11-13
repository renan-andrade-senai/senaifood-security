package br.com.senaifood.security.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.senaifood.security.dto.ClienteDto;

@FeignClient("cliente")
public interface ClienteClient {
	
	@GetMapping("/cliente/idusuario/{id}")
	public ClienteDto getClientePorId(@PathVariable Long id);

}
