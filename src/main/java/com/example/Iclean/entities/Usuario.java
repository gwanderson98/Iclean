package com.example.Iclean.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter,
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id") 
@Table(name = "tb_usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String senha;
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private Set<Endereco> enderecos = new HashSet<>();

	// @JsonIgnore
	@OneToMany(mappedBy = "prestador")
	private List<Anuncio> anuncios = new ArrayList<>();

	// @JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<OrdemServico> OrdemServicos = new ArrayList<>();

	// @JsonIgnore
	@ManyToMany
	private List<Especialidade> especialidades = new ArrayList<>();


	public Set<Usuario> getEnderecos() {
        return enderecos.stream().map(Endereco::getUsuario).collect(Collectors.toSet());
    }

}
