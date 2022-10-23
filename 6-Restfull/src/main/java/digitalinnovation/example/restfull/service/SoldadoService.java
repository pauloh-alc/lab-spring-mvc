package digitalinnovation.example.restfull.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import digitalinnovation.example.restfull.controller.request.SoldadoEditRequest;
import digitalinnovation.example.restfull.controller.response.SoldadoResponse;
import digitalinnovation.example.restfull.dto.Soldado;
import digitalinnovation.example.restfull.entity.SoldadoEntity;
import digitalinnovation.example.restfull.repository.SoldadoRepository;

@Service
public class SoldadoService {
	
	private SoldadoRepository soldadoRepository;
	private ObjectMapper objectMapper;
	
	@Autowired
	public SoldadoService(SoldadoRepository soldadoRepository, ObjectMapper objectMapper) {
		this.soldadoRepository = soldadoRepository;
		this.objectMapper = objectMapper;
	}
	
	public List<SoldadoResponse> buscarTodosSoldados() {	
		List<SoldadoResponse> soldados = soldadoRepository.findAll().stream()
				.map(it -> objectMapper.convertValue(it, SoldadoResponse.class))
				.collect(Collectors.toList());
		
		return soldados;
	}
	
	public SoldadoResponse buscarSoldado(Long id) {
		SoldadoEntity soldado = soldadoRepository.findById(id).orElseThrow();
		SoldadoResponse soldadoResponse = objectMapper.convertValue(soldado, SoldadoResponse.class);
		return soldadoResponse;
	}
	
	public SoldadoEntity criarSoldado(Soldado dto) {
		SoldadoEntity soldadoEntity = objectMapper.convertValue(dto, SoldadoEntity.class);
		soldadoRepository.save(soldadoEntity);
		return soldadoEntity;
	}

	public void alterarSoldado(Long id, SoldadoEditRequest soldadoEditRequest) {
		SoldadoEntity soldadoEntity = objectMapper.convertValue(soldadoEditRequest, SoldadoEntity.class);
        soldadoEntity.setId(id);
        soldadoRepository.save(soldadoEntity);
	}

	public void deletarSoldado(Long id) {
		SoldadoEntity soldado = soldadoRepository.findById(id).orElseThrow();
		soldadoRepository.delete(soldado);
	}
}
