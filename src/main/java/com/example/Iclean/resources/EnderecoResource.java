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

import com.example.Iclean.dto.EnderecoDTO;
import com.example.Iclean.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService service;
	
	@GetMapping
	public ResponseEntity<Page<EnderecoDTO>> findAllPaged(
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linePerPage", defaultValue = "12") Integer linePerPage,
			@RequestParam(value="orderBy", defaultValue = "logradouro") String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC") String direction			
			){
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);		
		Page<EnderecoDTO> list = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id){
		EnderecoDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/meusenderecos")
	public ResponseEntity<List<EnderecoDTO>> findByUsuario(){
		List<EnderecoDTO> list = service.findByUsuario();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<EnderecoDTO> insert(@RequestBody EnderecoDTO dto){
		EnderecoDTO newDTO = service.insert(dto);
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
	public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @RequestBody EnderecoDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
}
