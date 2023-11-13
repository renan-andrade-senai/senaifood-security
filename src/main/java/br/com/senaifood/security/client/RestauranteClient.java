package br.com.senaifood.security.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.senaifood.security.dto.RestauranteDto;

@FeignClient("restaurante")
public interface RestauranteClient {

	@GetMapping("/restaurante/idusuario/{id}")
	public RestauranteDto getRestaurantePorId(@PathVariable Long id);
	
}
