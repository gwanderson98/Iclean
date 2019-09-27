package com.example.Iclean.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Usuario;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	List<Endereco> findByUsuario(Usuario usuario);
}
