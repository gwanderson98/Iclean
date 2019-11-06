package com.example.Iclean.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Iclean.dto.AnuncioDTO;
import com.example.Iclean.services.AnuncioService;

@RestController
@RequestMapping(value = "/anuncios")
public class AnuncioResource {

	@Autowired
	private AnuncioService service;
	
	@GetMapping
	public ResponseEntity<List<AnuncioDTO>> findAll(){
		List<AnuncioDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AnuncioDTO> findById(@PathVariable Long id){
		AnuncioDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
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
	
	@PutMapping(value = "/{id}/alterarstatus")
	public ResponseEntity<Void> updateStatus(@PathVariable Long id){
		service.alterarStatus(id);
		return ResponseEntity.noContent().build();
	}
}
