package com.example.Iclean.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.Usuario;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

	List<OrdemServico> findByCliente(Usuario client);
}