package com.example.Iclean.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Usuario;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{
	
	List<Anuncio> findByPrestador(Usuario usuario);
}
