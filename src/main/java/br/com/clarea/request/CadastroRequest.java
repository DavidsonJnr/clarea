package br.com.clarea.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.clarea.entity.constants.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CadastroRequest {

	private String name;
	private String email;
	private String password;
	private Set<ERole> role;
	
}
