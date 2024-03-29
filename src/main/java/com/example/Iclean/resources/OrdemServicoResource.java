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

import com.example.Iclean.dto.OrdemServicoDTO;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.services.OrdemServicoService;

@RestController
@RequestMapping(value = "/ordensServicos")
public class OrdemServicoResource {

	@Autowired
	private OrdemServicoService service;
	
	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> findAll(){
		List<OrdemServicoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Long id){
		OrdemServicoDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<OrdemServico> insert(@RequestBody OrdemServico obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> update(@PathVariable Long id, @RequestBody OrdemServicoDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}/cancelarservico")
	public ResponseEntity<OrdemServicoDTO> cancelarServico(@PathVariable Long id){
		OrdemServicoDTO dto = service.cancelar(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}/aceitarservico")
	public ResponseEntity<OrdemServicoDTO> aceitarServico(@PathVariable Long id){
		OrdemServicoDTO dto = service.aceitar(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}/rejeitarservico")
	public ResponseEntity<OrdemServicoDTO> rejeitarServico(@PathVariable Long id){
		OrdemServicoDTO dto = service.rejeitar(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}/concluirservico")
	public ResponseEntity<OrdemServicoDTO> concluirServico(@PathVariable Long id){
		OrdemServicoDTO dto = service.concluir(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}/avaliarcliente")
	public ResponseEntity<OrdemServicoDTO> avaliarCliente(@PathVariable Long id, @RequestBody int avaliacao){
		OrdemServicoDTO dto = service.avaliarcliente(id, avaliacao);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}/avaliarprestador")
	public ResponseEntity<OrdemServicoDTO> avaliarPrestador(@PathVariable Long id, @RequestBody int avaliacao){
		OrdemServicoDTO dto = service.avaliarprestador(id, avaliacao);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value="/minhasOrdens")
	public ResponseEntity<List<OrdemServicoDTO>> findByUsuario(){
		List<OrdemServicoDTO> list = service.findByUsuario();
		return ResponseEntity.ok().body(list);
	}
}
