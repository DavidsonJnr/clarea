package br.com.clarea.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.clarea.entity.constants.FormaPagamento;
import br.com.clarea.entity.constants.StatusPagamento;
import br.com.clarea.entity.constants.TipoServico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "servico")
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Servico extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_servico", nullable = false, length = 50)
	private TipoServico tipoServico;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Builder.Default
	private LocalDateTime dataAgendamento = LocalDateTime.now();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "agenda_id", referencedColumnName = "id", nullable = false)
	private Agenda agenda;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false, length = 50)
	private FormaPagamento formaPagamento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_pagamento", nullable = false, length = 50)
	private StatusPagamento statusPagamento;
}
