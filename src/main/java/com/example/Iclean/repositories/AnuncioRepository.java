package com.example.Iclean.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Iclean.entities.Anuncio;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{

}
