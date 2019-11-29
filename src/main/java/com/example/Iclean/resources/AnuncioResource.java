package com.example.Iclean.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Iclean.dto.AnuncioDTO;
import com.example.Iclean.entities.PalavraChave;
import com.example.Iclean.services.AnuncioService;

@RestController
@RequestMapping(value = "/anuncios")
public class AnuncioResource {

	@Autowired
	private AnuncioService service;
	
	@GetMapping
	public ResponseEntity<Page<AnuncioDTO>> findAllPaged(
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linePerPage", defaultValue = "10") Integer linePerPage,
			@RequestParam(value="status", defaultValue = "true") Boolean status,
			@RequestParam(value="orderBy", defaultValue = "titulo") String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC") String direction			
			){
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		Page<AnuncioDTO> list = service.findAllPaged(pageRequest, status);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AnuncioDTO> findById(@PathVariable Long id){
		AnuncioDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}/palavraschaves")
	public ResponseEntity<List<PalavraChave>> findPalavrasChaves(@PathVariable Long id){
		List<PalavraChave> list = service.findPalavrasChaves(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<AnuncioDTO> insert(@RequestBody AnuncioDTO dto){
		AnuncioDTO newDTO = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AnuncioDTO> update(@PathVariable Long id, @RequestBody AnuncioDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/especialidade/{id}")
	public ResponseEntity<List<AnuncioDTO>> anuncioEspecialidade( @PathVariable Long id){
		List<AnuncioDTO> list = service.anuncioEspecialidade(id);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/palavrachave/{palavra}")
	public ResponseEntity<List<AnuncioDTO>> anuncioPalavraChave( @PathVariable("palavra") String palavra){
		List<AnuncioDTO> list = service.anuncioPalavraChave(palavra);
		return ResponseEntity.ok().body(list); 
	}
	
	@GetMapping(value = "/titulo")
	public ResponseEntity<Page<AnuncioDTO>> anuncioTitulo(
			@RequestParam(value ="palavra", defaultValue = "") String palavra,
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linePerPage", defaultValue = "10") Integer linePerPage,
			@RequestParam(value="status", defaultValue = "true") Boolean status,
			@RequestParam(value="orderBy", defaultValue = "titulo") String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC") String direction){
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		Page<AnuncioDTO> list = service.anuncioTitulo(palavra, pageRequest);
		return ResponseEntity.ok().body(list); 
	}

	@PutMapping(value = "/{id}/alterarstatus")
	public ResponseEntity<Void> updateStatus(@PathVariable Long id){
		service.alterarStatus(id);
		return ResponseEntity.noContent().build();
	}
}
