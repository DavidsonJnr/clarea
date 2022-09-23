package br.com.clarea.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Immutable
@Entity
@Table(name = "vw_viewuser")
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class UserView {

	@Id
	private Long userId;

	private String email;

	private String name;

	private Boolean active;

	private LocalDateTime dataCadastro;

}
