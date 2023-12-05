package br.com.senaifood.security.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.senaifood.security.dto.EntregadorDto;

@FeignClient("entrega")
public interface EntregadorClient {
	
	@GetMapping("/entregador/idusuario/{id}")
	public EntregadorDto getEntregadorPorId(@PathVariable Long id);

}
