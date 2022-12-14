package br.com.clarea.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity {

	@Column(nullable = false, name = "data_cadastro")
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Version
	@Column(nullable = false, columnDefinition = "integer default 0")
	private Integer versao;
	
}
