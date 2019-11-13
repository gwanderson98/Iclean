package com.example.Iclean.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Iclean.entities.Anuncio;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Anuncio obj WHERE obj.status = true")
	Page<Anuncio> findAllByAtivo(Pageable pageable);

}
