package com.example.Iclean.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.PalavraChave;

@Repository
public interface PalavraChaveRepository extends JpaRepository<PalavraChave, Long>{

	List<PalavraChave> findByAnuncio(Anuncio anuncio);
}
