package com.example.Iclean.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Iclean.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
