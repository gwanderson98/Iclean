package com.example.Iclean.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Iclean.entities.PalavraChave;

@Repository
public interface PalavraChaveRepository extends JpaRepository<PalavraChave, Long>{

}
