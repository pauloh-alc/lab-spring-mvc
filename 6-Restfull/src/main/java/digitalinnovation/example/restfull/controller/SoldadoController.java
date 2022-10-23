package digitalinnovation.example.restfull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import digitalinnovation.example.restfull.controller.request.SoldadoEditRequest;
import digitalinnovation.example.restfull.controller.response.SoldadoResponse;
import digitalinnovation.example.restfull.dto.Soldado;
import digitalinnovation.example.restfull.entity.SoldadoEntity;
import digitalinnovation.example.restfull.service.SoldadoService;

@RestController
@RequestMapping("/v1/soldado")
public class SoldadoController {
	
	private SoldadoService soldadoService;	
	
	@Autowired
	public SoldadoController(SoldadoService soldadoService, ObjectMapper objectMapper) {
		this.soldadoService = soldadoService;
	}
	
	@GetMapping
	public ResponseEntity<List<SoldadoResponse>> buscarTodosSoldados() {
		List<SoldadoResponse> soldados = soldadoService.buscarTodosSoldados();
		return ResponseEntity.ok().body(soldados);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SoldadoResponse> buscarSoldado(@PathVariable("id") final Long id) {
		SoldadoResponse soldado = soldadoService.buscarSoldado(id);
		return ResponseEntity.status(HttpStatus.OK).body(soldado);
	}
	
	@PostMapping
	public ResponseEntity<SoldadoEntity> criarSoldado(@RequestBody Soldado soldado) {
		SoldadoEntity soldadoEntity =  soldadoService.criarSoldado(soldado);
		return ResponseEntity.status(HttpStatus.CREATED).body(soldadoEntity);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Soldado> editarSoldado(@PathVariable("id") Long id, @RequestBody SoldadoEditRequest soldadoEditRequest) {
		soldadoService.alterarSoldado(id, soldadoEditRequest);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletarSoldado(@PathVariable("id") Long id) {
		soldadoService.deletarSoldado(id);
	}
}
